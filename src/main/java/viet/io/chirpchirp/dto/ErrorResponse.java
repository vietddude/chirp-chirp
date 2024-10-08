package viet.io.chirpchirp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import viet.io.chirpchirp.util.enums.ResponseStatus;
import viet.io.chirpchirp.util.enums.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String status;
    private ErrorCode code;
    private String message;

    public ErrorResponse(ResponseStatus status, ErrorCode code, String message) {
        this.status = status.getValue();
        this.code = code;
        this.message = message;
    }
}