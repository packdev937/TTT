package dev.pack.notiontalks.notion.service;

import dev.pack.notiontalks.notion.config.NotionConfigProperties;
import dev.pack.notiontalks.notion.model.Database;
import dev.pack.notiontalks.notion.model.Page;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DatabaseService {

    private final Logger log = LoggerFactory.getLogger(DatabaseService.class);
    private final NotionConfigProperties notionConfigProperties;
    private final RestTemplate restTemplate;

    private DatabaseService(NotionConfigProperties notionConfigProperties,
        RestTemplate restTemplate) {
        this.notionConfigProperties = notionConfigProperties;
        this.restTemplate = restTemplate;
    }

    public List<Page> query(String databaseId) {
        String url = notionConfigProperties.apiUrl() + "/v1/databases/" + databaseId + "/query";
        log.info("Querying Notion database: {}", url);

        ResponseEntity<Database> db = restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<>(getDefaultHeaders()),
            Database.class
        );
        return db.getBody().getPages();
    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Notion-Version", notionConfigProperties.apiVersion());
        headers.set("Authorization", notionConfigProperties.authToken());

        return headers;
    }
}
