package dev.pack.ttt.service;

import com.fasterxml.jackson.databind.JsonNode;
import dev.pack.ttt.model.Content;
import dev.pack.ttt.model.Status;
import dev.pack.ttt.notion.NotionClient;
import dev.pack.ttt.notion.config.NotionConfigProperties;
import dev.pack.ttt.notion.model.Page;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotionService {

    private final NotionClient notionClient;
    private final NotionConfigProperties notionConfigProperties;

    public static Content mapPageToContent(Page page) {
        if (page == null) {
            throw new RuntimeException("Properties in Page object is null");
        }
        String databaseId = page.getParent().get("database_id").asText();
        String pageId = page.getId();
        String title = page.getProperties().get("Title").get("title").get(0).get("text")
            .get("content").asText();
        LocalDate createdDate = LocalDate.parse(
            page.getProperties().get("Date").get("date").get("start").asText(),
            DateTimeFormatter.ISO_LOCAL_DATE);
        String category = page.getProperties().get("Category").get("select").get("name").asText();
        Status status = Status.valueOf(
            page.getProperties().get("Status").get("select").get("name").asText());

        return new Content(databaseId, pageId, title, createdDate, category, status);
    }

}
