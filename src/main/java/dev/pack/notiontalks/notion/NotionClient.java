package dev.pack.notiontalks.notion;

import dev.pack.notiontalks.notion.service.DatabaseService;
import org.springframework.stereotype.Component;

@Component
public class NotionClient {

    // Why public?
    public final DatabaseService databaseService;

    public NotionClient(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // client.databases.query({database_id})

}
