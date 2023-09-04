package dev.pack.ttt.notion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Page {

    private String object;
    private String id;
    @JsonProperty("created_time")
    private LocalDateTime createdTime;
    @JsonProperty("last_edited_time")
    private LocalDateTime lastEditedTime;
    private Boolean archive;
    private String url;

    // properties are consist of dynamic information
    private JsonNode properties;
    private JsonNode parent;
}
