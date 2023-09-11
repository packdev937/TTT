package dev.pack.ttt.notion.model;

import dev.pack.ttt.notion.model.Status;
import java.time.LocalDate;

public record Content(String databaseId, String pageId, String title, LocalDate createdDate,
                      String category, Status status) {

    @Override
    public String databaseId() {
        return databaseId;
    }

    @Override
    public String pageId() {
        return pageId;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public LocalDate createdDate() {
        return createdDate;
    }

    @Override
    public String category() {
        return category;
    }

    @Override
    public Status status() {
        return status;
    }
}
