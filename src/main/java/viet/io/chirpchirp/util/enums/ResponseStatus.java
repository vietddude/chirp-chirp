package viet.io.chirpchirp.util.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    SUCCESS("success"),
    ERROR("error");

    private final String value;

    // Constructor
    ResponseStatus(String value) {
        this.value = value;
    }

}
