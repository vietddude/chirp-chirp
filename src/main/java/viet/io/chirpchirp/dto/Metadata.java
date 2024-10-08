package viet.io.chirpchirp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Metadata {
    private int totalCount;
    private int page;
    private int perPage;
    private int totalPages;
}