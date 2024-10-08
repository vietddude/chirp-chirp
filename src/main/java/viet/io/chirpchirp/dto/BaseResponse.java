package viet.io.chirpchirp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import viet.io.chirpchirp.util.enums.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse<T> {
    private ResponseStatus status;
    private T data;
}