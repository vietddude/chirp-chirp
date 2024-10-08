package viet.io.chirpchirp.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import viet.io.chirpchirp.BaseTest;
import viet.io.chirpchirp.dto.HealthCheckResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("integration")
public class ServerIntegrationTest extends BaseTest {

    @Test
    @DisplayName("Health check endpoint returns OK")
    public void testServerIsRunningAndReturnsResponse() {
        String url = "http://localhost:" + port + "/health";

        // Sending a GET request to the API
        ResponseEntity<HealthCheckResponse> response = restTemplate.getForEntity(url, HealthCheckResponse.class);

        // Verifying the status and response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Service is running", response.getBody().getMessage());
    }
}