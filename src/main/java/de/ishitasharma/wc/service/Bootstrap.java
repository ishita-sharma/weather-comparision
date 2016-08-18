package de.ishitasharma.wc.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

import de.ishitasharma.wc.entity.City;
import de.ishitasharma.wc.helper.JsonHelperUtil;

@Component
public class Bootstrap implements InitializingBean {

	private static final String CITY_DATA_FILE = "data/city.list.json";
	private static final String INSERT_CITY_DATA = "INSERT INTO wc.city_ref(city_name, city_id, country, lat, lon)VALUES (?, ?, ?, ?, ?) ON CONFLICT ON CONSTRAINT city_id_pk DO NOTHING";
	private String cityDataDump = "";
	private String formattedCityData = "";
	private JsonHelperUtil<List<City>> jsonHelper = new JsonHelperUtil<List<City>>();

	private int rowsAdded = 0;
	private int rowsSkipped = 0;

	@Inject
	private DataSource dataSource;

	@Override
	public void afterPropertiesSet() throws Exception {
		cityDataDump = loadCityData();
		formattedCityData = formatCityData(cityDataDump);
		List<City> listOfCities = (List<City>) jsonHelper.deSerializeJsonToObject(
				formattedCityData, new TypeReference<List<City>>() {
				});
		storeCityDataInDB(listOfCities);
		stats();
	}

	private String loadCityData() throws IOException {
		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		return IOUtils
				.toString(classLoader.getResourceAsStream(CITY_DATA_FILE));
	}

	private String formatCityData(String cityDataDump) {
		StringBuilder temp = new StringBuilder();
		temp.append('[');
		temp.append(String.join(",", cityDataDump.split("\\r?\\n")));
		temp.append(']');
		return temp.toString();
	}

	private void storeCityDataInDB(List<City> listOfCities) {

		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement preparedStatement = null;
			preparedStatement = conn.prepareStatement(INSERT_CITY_DATA);
			int batchSize = 0;
			System.out.println(listOfCities.size() + " cities fetched.");
			for (City city : listOfCities) {

				preparedStatement.setString(1, city.getCityName());
				preparedStatement.setInt(2, city.getCityId());
				preparedStatement.setString(3, city.getCountryName());
				preparedStatement.setDouble(4, city.getCityCoordinates()
						.getLat());
				preparedStatement.setDouble(5, city.getCityCoordinates()
						.getLon());
				preparedStatement.addBatch();
				batchSize++;
				if (batchSize == 1000) {
					int[] results = preparedStatement.executeBatch();
					stats(results);
					batchSize = 0;
				}
			}
			if (batchSize > 0) {
				int[] results = preparedStatement.executeBatch();
				stats(results);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private void stats(int[] results) {
		for (int result : results) {
			if (result > 0)
				rowsAdded++;
			if (result == 0)
				rowsSkipped++;
		}
	}

	private void stats() {
		System.out.println("Data sync summary:" + rowsAdded
				+ " cities added and " + rowsSkipped + " cities skipped.");
	}
}
