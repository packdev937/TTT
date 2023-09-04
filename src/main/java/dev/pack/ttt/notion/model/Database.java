package dev.pack.ttt.notion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Database<T> {

    private String object;
    @JsonProperty("results")
    private List<T> results = new ArrayList<>();
    @JsonProperty("next_cursor")
    private Boolean nextCursor;
    @JsonProperty("has_more")
    private Boolean hasMore;
}
