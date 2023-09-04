package dev.pack.ttt.controller;

import dev.pack.ttt.model.Content;
import dev.pack.ttt.notion.NotionClient;
import dev.pack.ttt.notion.config.NotionConfigProperties;
import dev.pack.ttt.notion.model.Page;
import dev.pack.ttt.service.NotionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotionController {

    private final NotionClient notionClient;
    private final NotionConfigProperties notionConfigProperties;

    @GetMapping("/pages")
    public List<Content> findAll() {
        List<Page> pages = notionClient.databaseService.query(notionConfigProperties.databaseId());
        return pages.stream().map(NotionService::mapPageToContent).toList();
    }
}
