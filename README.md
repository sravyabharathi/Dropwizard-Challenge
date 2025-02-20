This project is a demonstration of a geolocation application using Dropwizard.
# geoLocationApp

he IP Geolocation application is designed to provide geolocation data for a given IP address by first checking a local cache, then a database, and finally falling back to an external API if necessary.

## Features

- Search by ip 
- Display status,message,country,countryCode,region,regionName,city,zip,lat,lon,timezone,isp,org,as and query

## Installation

 git clone https://github.com/sravyabharathi/Dropwizard-Challenge.git
 cd Dropwizard-Challenge
 mvn clean package
 mvn exec:java -Dexec.mainClass="com.geolocations.IpGeolocationApplication" -Dexec.args="server config.yml"

### Prerequisites
Dropwizard 4.0.12
JDBI 3.18.0
SQLite JDBC 3.36.0.3
Guava 31.1-jre

### Clone the Repository

```sh
git clone https://github.com/sravyabharathi/Dropwizard-Challenge.git
cd Dropwizard-Challenge


Challenge:
----------
This code challenge is based on the IP geolocation provided by https://ip-api.com/docs/api:json

1. Create a relational database schema where you can store the results from the API call
2. Implement the database schema from the first step using a database of your choice (you can use in-memory SQLite as well)
3. Create an API endpoint that will take an IP address as parameter (path or query is up to you) and will return its geolocation data by first checking if we have the data on the database and falling back to the external API call
4. Use a time-based (set at 1 minute) expiring cache to return the data before querying the database again
5. Think about a way to automatically test this endpoint so that you end up with reliable and reproducible results

Optional steps:
Extend your database schema adding a new column with the insertion timestamp
Modify the third point to refresh the geolocation data if the timestamp is older than 5 minutes
Take error handling and logging in consideration and think about ways of validating the input data

Hints:
1.Build the service using Dropwizard
2.Connect to the database using JDBI
3.Dropwizard includes Guava, so you can use its CacheBuilder to build the cache
4.Dropwizard includes SLF4J
5.Dropwizard includes Hibernate Validator


Thank you for taking this challenge and we hope you will enjoy working on it. 
