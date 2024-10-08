package viet.io.chirpchirp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HealthCheckResponse {
    private String status;
    private String message;
}
