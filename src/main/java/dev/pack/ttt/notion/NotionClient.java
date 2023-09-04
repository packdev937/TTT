package dev.pack.ttt.notion;

import dev.pack.ttt.notion.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotionClient {

    // Why declare DatabaseService as PUBLIC?
    // Used Example Code: List<Page> pages = notionClient.databaseService.query(notionConfigProperties.databaseId());
    public final DatabaseService databaseService;
}
