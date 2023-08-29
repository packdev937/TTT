package dev.pack.notiontalks.notion;

import dev.pack.notiontalks.notion.service.DatabaseService;

public class NotionClient {

    // Why public?
    public final DatabaseService databaseService;

    public NotionClient(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }


}
