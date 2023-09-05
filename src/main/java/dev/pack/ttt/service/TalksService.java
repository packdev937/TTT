package dev.pack.ttt.service;

import com.fasterxml.jackson.databind.JsonNode;
import dev.pack.ttt.model.Content;
import dev.pack.ttt.notion.model.Page;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class TalksService {

    public static Content mapPageToTalk(Page page) {
        JsonNode properties = page.getProperties();
        String databaseId = page.getProperties().get("parent").get("type").asText();
        LocalDateTime startDate = extractJsonLocalDateTimeValue(properties, "StartDate", "date",
            "start");
        LocalDateTime endDate = extractJsonLocalDateTimeValue(properties, "EndDate", "date",
            "start");
        String url = page.getProperties().get("URL").get("url").asText();
        String recording = page.getProperties().get("Recording").get("url").asText();
        return null;
    }

    private static String extractJsonStringValue(JsonNode properties, String... keys) {
        JsonNode node = properties;
        for (String key : keys) {
            node = node.get(key);
            if (node == null) {
                return ""; // or throw an exception or provide a default value
            }
        }
        return node.asText();
    }

    private static LocalDateTime extractJsonLocalDateTimeValue(JsonNode properties,
        String... keys) {
        String dateString = extractJsonStringValue(properties, keys);
        if (!dateString.isEmpty()) {
            return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
        return null; // or provide a default value or throw an exception
    }
}