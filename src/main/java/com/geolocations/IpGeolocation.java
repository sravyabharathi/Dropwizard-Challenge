package com.geolocations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpGeolocation {
    @JsonProperty
    private String query;
    @JsonProperty("status")
    private String apiStatus;
    @JsonProperty
    private String country;
    @JsonProperty
    private String countryCode;
    @JsonProperty
    private String region;
    @JsonProperty
    private String regionName;
    @JsonProperty
    private String city;
    @JsonProperty
    private String zip;
    @JsonProperty
    private double lat;
    @JsonProperty
    private double lon;
    @JsonProperty
    private String timezone;
    @JsonProperty
    private String isp;
    @JsonProperty
    private String org;
    @JsonProperty("as")
    private String asInfo;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getAsInfo() {
        return asInfo;
    }

    public void setAsInfo(String asInfo) {
        this.asInfo = asInfo;
    }

    public String getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }
}