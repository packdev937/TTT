package dev.pack.ttt.controller;

import dev.pack.ttt.model.Content;
import dev.pack.ttt.notion.NotionClient;
import dev.pack.ttt.notion.config.NotionConfigProperties;
import dev.pack.ttt.notion.model.Page;
import dev.pack.ttt.service.TalksService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talks")
public class TalkController {

    private final NotionClient notionClient;
    private final NotionConfigProperties notionConfigProperties;

    public TalkController(NotionClient notionClient,
        NotionConfigProperties notionConfigProperties) {
        this.notionClient = notionClient;
        this.notionConfigProperties = notionConfigProperties;
    }

    @GetMapping
    public List<Content> findAll() {
        List<Page> pages = notionClient.databaseService.query(notionConfigProperties.databaseId());
        return pages.stream().map(TalksService::mapPageToTalk).toList();
    }
}
