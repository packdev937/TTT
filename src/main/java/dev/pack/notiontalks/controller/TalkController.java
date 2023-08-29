package dev.pack.notiontalks.controller;

import dev.pack.notiontalks.model.Talk;
import dev.pack.notiontalks.notion.NotionClient;
import dev.pack.notiontalks.notion.config.NotionConfigProperties;
import dev.pack.notiontalks.notion.model.Page;
import dev.pack.notiontalks.service.TalksService;
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
    public List<Talk> findAll() {
        List<Page> pages = notionClient.databaseService.query(notionConfigProperties.databaseId());
        return pages.stream().map(TalksService::mapPageToTalk).toList();
    }
}
