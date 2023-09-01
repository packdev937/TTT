package dev.pack.ttt.model;

import java.time.LocalDateTime;

public record Content(String databaseId, String pageId, String title, LocalDateTime createdDate,
                      String category, Status status) {

}
