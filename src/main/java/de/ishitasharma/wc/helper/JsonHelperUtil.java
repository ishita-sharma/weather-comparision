package de.ishitasharma.wc.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelperUtil<T> {

	private ObjectMapper mapper = new ObjectMapper();

	public JsonHelperUtil() {
		super();
	}

	public T deSerializeJsonToObject(String jsonString,
			TypeReference<T> valueTypeRef) throws JsonParseException,
			JsonMappingException, IOException {
		return mapper.readValue(jsonString, valueTypeRef);
	}

	public T deSerializeJsonToObject(String jsonString, Class<T> valueType)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonString, valueType);
	}
}
