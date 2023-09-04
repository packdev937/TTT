package dev.pack.ttt.notion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;

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

    public JsonNode getParent() {
        return parent;
    }

    public void setParent(JsonNode parent) {
        this.parent = parent;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLastEditedTime() {
        return lastEditedTime;
    }

    public void setLastEditedTime(LocalDateTime lastEditedTime) {
        this.lastEditedTime = lastEditedTime;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JsonNode getProperties() {
        return properties;
    }

    public void setProperties(JsonNode properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Page{" +
            "object='" + object + '\'' +
            ", id='" + id + '\'' +
            ", createdTime=" + createdTime +
            ", last_edited_time=" + lastEditedTime +
            ", archive=" + archive +
            ", url='" + url + '\'' +
            ", properties=" + properties +
            '}';
    }
}
