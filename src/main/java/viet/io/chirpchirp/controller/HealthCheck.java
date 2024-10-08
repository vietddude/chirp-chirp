package viet.io.chirpchirp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viet.io.chirpchirp.dto.HealthCheckResponse;

@RestController
@RequestMapping("/health")
@Tag(name = "Health Check", description = "Health Check API")
public class HealthCheck {

    @GetMapping
    public HealthCheckResponse healthCheck() {
        return new HealthCheckResponse("OK", "Service is running");
    }
}
