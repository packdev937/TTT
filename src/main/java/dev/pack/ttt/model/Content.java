package dev.pack.ttt.model;

import java.time.LocalDate;

public record Content(String databaseId, String pageId, String title, LocalDate createdDate,
                      String category, Status status) {

}
