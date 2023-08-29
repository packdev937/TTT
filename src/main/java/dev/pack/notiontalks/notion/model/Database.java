package dev.pack.notiontalks.notion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String object;
    @JsonProperty("results")
    private List<Page> pages = new ArrayList<>();
    @JsonProperty("next_cursor")
    private Boolean nextCursor;
    @JsonProperty("has_more")
    private Boolean hasMore;

    public String getObject() {
        return object;
    }

    public List<Page> getPages() {
        return pages;
    }

    public Boolean getNextCursor() {
        return nextCursor;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public void setNextCursor(Boolean nextCursor) {
        this.nextCursor = nextCursor;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    @Override
    public String toString() {
        return "Database{" +
            "object='" + object + '\'' +
            ", pages=" + pages +
            ", nextCursor=" + nextCursor +
            ", hasMore=" + hasMore +
            '}';
    }
}
