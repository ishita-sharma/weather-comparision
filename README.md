# weather-comparision

This API compares the temperature of two given cities by getting temperature data from 'open weather' api. 
The temperatures of the cities are cached and they are refreshed if the data is older than 2.5 hours.

An example request to see comparision of temperature between two cities can be: 

  /compare?firstCity=london&secondCity=frankfurt&appid=your_appid
  
 NOTE: Use your own open weather api appid instead of your_appid.
