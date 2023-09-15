package dev.pack.ttt.notion.service;

import dev.pack.ttt.notion.config.NotionConfigProperties;
import dev.pack.ttt.notion.model.Block;
import dev.pack.ttt.notion.model.Content;
import dev.pack.ttt.notion.model.Database;
import dev.pack.ttt.notion.model.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
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

        ResponseEntity<Database<Page>> db = restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<>(getDefaultHeaders()),
            new ParameterizedTypeReference<Database<Page>>() {
            }
        );
        return db.getBody().getResults();
    }

    public List<Block> block(String pageId) {
        String url =
            notionConfigProperties.apiUrl() + "/v1/blocks/" + pageId + "/children?page_size=100";
        log.info("Getting Notion block: {}", url);

        ResponseEntity<Database<Block>> db = restTemplate.exchange(
            url,
            HttpMethod.GET,
            new HttpEntity<>(getDefaultHeaders()),
            new ParameterizedTypeReference<Database<Block>>() {
            }
        );
        return db.getBody().getResults();
    }

    public String patch(String pageId) {
        String url =
            notionConfigProperties.apiUrl() + "/v1/pages/" + pageId;
        log.info("Updating Notion page: {}", url);

        Map<String, Object> selectProperty = new HashMap<>();
        selectProperty.put("name", "COMPLETE");

        Map<String, Object> statusProperty = new HashMap<>();
        statusProperty.put("select", selectProperty);

        Map<String, Object> properties = new HashMap<>();
        properties.put("Status", statusProperty);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("properties", properties);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody,
            getDefaultHeaders());

        ResponseEntity<String> db = restTemplate.exchange(
            url,
            HttpMethod.PATCH,
            requestEntity,
            new ParameterizedTypeReference<String>() {
            }
        );
        return db.getBody();
    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Notion-Version", notionConfigProperties.apiVersion());
        headers.set("Authorization", notionConfigProperties.authToken());

        return headers;
    }
}
