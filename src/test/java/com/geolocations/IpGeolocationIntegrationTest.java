package com.geolocations;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.ConfigOverride;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class IpGeolocationIntegrationTest {

    @RegisterExtension
    public static final DropwizardAppExtension<IpGeolocationConfiguration> RULE = new DropwizardAppExtension<>(
            IpGeolocationApplication.class, "config.yml",
            ConfigOverride.config("database.url", "jdbc:sqlite:memory:myDb"));

    @Test
    public void testGetGeolocation() {
        Client client = ClientBuilder.newClient();
        String ip = "8.8.8.8";
        Response response = client.target(
                String.format("http://localhost:%d/geolocation?ip=%s", RULE.getLocalPort(), ip))
                .request()
                .get();

        assertEquals(200, response.getStatus());
        IpGeolocation geo = response.readEntity(IpGeolocation.class);
        assertEquals(ip, geo.getQuery());
        assertEquals("success", geo.getApiStatus());
    }

}
