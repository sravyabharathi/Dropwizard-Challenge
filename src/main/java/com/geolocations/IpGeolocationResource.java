package com.geolocations;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/geolocation")
@Produces(MediaType.APPLICATION_JSON)
public class IpGeolocationResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(IpGeolocationResource.class);

    // Regular expression pattern for validating IPv4 addresses
    private static final Pattern IP_ADDRESS_PATTERN = Pattern.compile(
            "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    private final IpGeolocationDao dao;
    // Cache to store geolocation data with a 1-minute expiration time
    private final Cache<String, IpGeolocation> cache;

    public IpGeolocationResource(IpGeolocationDao dao) {
        this.dao = dao;
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build();
    }

    // Endpoint to get geolocation data for a given IP address
    @GET
    public Response getGeolocation(@QueryParam("ip") String ip) {
        if (ip == null || ip.isEmpty() || !isValidIp(ip)) {
            LOGGER.warn("Invalid IP address: {}", ip);

            return Response.status(Response.Status.BAD_REQUEST).entity("Please enter the valid IP address").build();
        }
        try {
            IpGeolocation geo = cache.getIfPresent(ip);

            if (geo == null) {
                Optional<IpGeolocation> geoFromDb = dao.findByIpAddress(ip);
                if (geoFromDb.isPresent()) {
                    geo = geoFromDb.get();
                    cache.put(ip, geo);
                } else {
                    geo = fetchGeolocationFromApi(ip);
                    if (geo != null) {
                        dao.insert(ip, geo);
                        cache.put(ip, geo);
                    } else {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity("Failed to fetch geolocation data").build();
                    }
                }
            }
            return Response.ok(geo).build();
        } catch (Exception e) {

            LOGGER.error("Error handling request for IP: {}", ip, e);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();

        }
    }

    // Validate the IP address using the regular expression pattern
    private boolean isValidIp(String ip) {
        return IP_ADDRESS_PATTERN.matcher(ip).matches();
    }

    // Fetch geolocation data from the external API
    public IpGeolocation fetchGeolocationFromApi(String ip) {
        try {
            URL url = new URL("http://ip-api.com/json/" + ip);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(connection.getInputStream(), IpGeolocation.class);
        } catch (IOException e) {
            LOGGER.error("Error fetching geolocation data for IP: {}", ip, e);
            return null;
        }
    }
}
