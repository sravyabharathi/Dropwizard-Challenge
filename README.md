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
