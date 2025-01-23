package com.geolocations;

import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface IpGeolocationDao {
    @SqlUpdate("CREATE TABLE IF NOT EXISTS ip_geolocation (ip_address TEXT PRIMARY KEY, country TEXT, countryCode TEXT, region TEXT, regionName TEXT, city TEXT, zip TEXT, lat REAL, lon REAL, timezone TEXT, isp TEXT, org TEXT, as_info TEXT, query TEXT, api_status TEXT, api_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)")
    void createTable();

    @SqlUpdate("INSERT OR REPLACE INTO ip_geolocation (ip_address, country, countryCode, region, regionName, city, zip, lat, lon, timezone, isp, org, as_info, query, api_status, api_timestamp) VALUES (:ip_address, :country, :countryCode, :region, :regionName, :city, :zip, :lat, :lon, :timezone, :isp, :org, :asInfo, :query, :apiStatus, CURRENT_TIMESTAMP)")
    void insert(@Bind("ip_address") String ipAddress, @BindBean IpGeolocation geo);

    @SqlQuery("SELECT * FROM ip_geolocation WHERE ip_address = :ip_address")
    @RegisterBeanMapper(IpGeolocation.class)
    Optional<IpGeolocation> findByIpAddress(@Bind("ip_address") String ipAddress);

    @SqlUpdate("UPDATE ip_geolocation SET country = :country, countryCode = :countryCode, region = :region, regionName = :regionName, city = :city, zip = :zip, lat = :lat, lon = :lon, timezone = :timezone, isp = :isp, org = :org, as_info = :asInfo, query = :query, api_status = :apiStatus, api_timestamp = CURRENT_TIMESTAMP WHERE ip_address = :ip_address")
    void update(@Bind("ip_address") String ipAddress, @BindBean IpGeolocation geo);

}
