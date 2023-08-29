package dev.pack.notiontalks.notion.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("notion")
public record NotionConfigProperties(String apiUrl, String apiVerstion, String authToken, String databaseId) {

}
