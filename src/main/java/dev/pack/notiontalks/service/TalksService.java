package dev.pack.notiontalks.service;

import dev.pack.notiontalks.model.Talk;
import dev.pack.notiontalks.notion.model.Page;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class TalksService {

    public static Talk mapPageToTalk(Page page) {
        return new Talk(
            page.getId(),
            page.getProperties().get("Title").get("title").get(0).get("text").get("content")
                .asText(),
            LocalDateTime.parse(
                page.getProperties().get("StartDate").get("date").get("start").asText(),
                DateTimeFormatter.ISO_DATE_TIME),
            LocalDateTime.parse(
                page.getProperties().get("EndDate").get("date").get("start").asText(),
                DateTimeFormatter.ISO_DATE_TIME),
            page.getProperties().get("URL").get("url").asText(),
            page.getProperties().get("Recording").get("url").asText()
        );
    }
}
