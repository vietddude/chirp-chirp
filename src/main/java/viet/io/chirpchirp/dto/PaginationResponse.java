package viet.io.chirpchirp.dto;

import lombok.Getter;
import lombok.Setter;
import viet.io.chirpchirp.util.enums.ResponseStatus;

@Getter
@Setter
public class PaginationResponse<T> extends BaseResponse<T> {
    private Metadata metadata;

    public PaginationResponse(ResponseStatus status, T data, Metadata metadata) {
        super(status, data);
        this.metadata = metadata;
    }
}