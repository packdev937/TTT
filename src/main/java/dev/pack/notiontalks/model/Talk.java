package dev.pack.notiontalks.model;

import java.time.LocalDateTime;

public record Talk(String id, String title, LocalDateTime startDate, LocalDateTime endDate,
                   String url, String recording) {

}
