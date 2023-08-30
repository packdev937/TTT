package dev.pack.notiontalks.service;

import com.fasterxml.jackson.databind.JsonNode;
import dev.pack.notiontalks.model.Talk;
import dev.pack.notiontalks.notion.model.Page;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class TalksService {

    public static Talk mapPageToTalk(Page page) {
        JsonNode properties = page.getProperties();
        if (properties == null) {
            // 적절한 예외 처리나 기본값 처리를 수행
            throw new RuntimeException("Properties in Page object is null");
        }

        String title = page.getProperties().get("Title").get("title").get(0).get("text").get("content").asText(); // 이상 없음
        LocalDateTime startDate = extractJsonLocalDateTimeValue(properties, "StartDate", "date", "start");
        LocalDateTime endDate = extractJsonLocalDateTimeValue(properties, "EndDate", "date", "start");
        String url = page.getProperties().get("URL").get("url").asText();
        // 여기서 오류가 발생
        // Recording 을 URL로 설정해두지 않았음 -> 자연스럽게 URL을 못찾으므로 NullPointerException
        String recording = page.getProperties().get("Recording").get("url").asText();

        return new Talk(page.getId(), title, startDate, endDate, url, recording);
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

    private static LocalDateTime extractJsonLocalDateTimeValue(JsonNode properties, String... keys) {
        String dateString = extractJsonStringValue(properties, keys);
        if (!dateString.isEmpty()) {
            return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
        return null; // or provide a default value or throw an exception
    }

//    public static Talk mapPageToTalk(Page page) {
//        return new Talk(
//            page.getId(),
//            page.getProperties().get("Title").get("title").get(0).get("text").get("content").asText(),
//            LocalDateTime.parse(page.getProperties().get("StartDate").get("date").get("start").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
//            LocalDateTime.parse(page.getProperties().get("EndDate").get("date").get("start").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
//            page.getProperties().get("URL").get("url").asText(),
//            page.getProperties().get("Recording").get("url").asText());
//    }
}
