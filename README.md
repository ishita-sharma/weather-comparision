# weather-comparision

[![Build Status](https://travis-ci.org/ishita-sharma/weather-comparision.svg?branch=master)](https://travis-ci.org/ishita-sharma/weather-comparision)

This API compares the temperature of two given cities by getting temperature data from 'open weather' api. 
The temperatures of the cities are cached and they are refreshed if the data is older than 2.5 hours.

An example request to see comparision of temperature between two cities can be: 

  /compare?firstCity=london&secondCity=frankfurt
  
 NOTE: Use your own open weather api appid as enviornment variable -Dapp_id=[app_id]

DEMO: https://lbjq7pyrrl.execute-api.eu-central-1.amazonaws.com/INT/weathercompare?firstCity=jaipur&secondCity=mumbai
