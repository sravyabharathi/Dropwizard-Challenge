package com.geolocations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.ws.rs.core.Response;

public class IpGeolocationResourceTest {
    @Mock
    private IpGeolocationDao dao;

    @InjectMocks
    private IpGeolocationResource resource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetGeolocation_FromDatabase() {
        String ip = "8.8.8.8";
        IpGeolocation expectedGeolocation = new IpGeolocation();
        expectedGeolocation.setQuery(ip);
        expectedGeolocation.setApiStatus("success");

        // Mock database call
        when(dao.findByIpAddress(ip)).thenReturn(Optional.of(expectedGeolocation));

        // Call the endpoint
        Response response = resource.getGeolocation(ip);

        // Verify the response
        assertEquals(200, response.getStatus());
        IpGeolocation actualGeolocation = (IpGeolocation) response.getEntity();
        assertEquals(expectedGeolocation.getQuery(), actualGeolocation.getQuery());
        assertEquals(expectedGeolocation.getApiStatus(), actualGeolocation.getApiStatus());

        verify(dao, times(1)).findByIpAddress(ip);
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void testGetGeolocation_FromExternalApi() {
        String ip = "8.8.8.8";
        IpGeolocation expectedGeolocation = new IpGeolocation();
        expectedGeolocation.setQuery(ip);
        expectedGeolocation.setApiStatus("success");

        // Mock external API call
        IpGeolocation actualGeolocation = resource.fetchGeolocationFromApi(ip);

        assertNotNull(actualGeolocation);
        assertEquals(expectedGeolocation.getQuery(), actualGeolocation.getQuery());
        assertEquals(expectedGeolocation.getApiStatus(), actualGeolocation.getApiStatus());
    }
}
