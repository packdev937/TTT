package dev.pack.ttt.notion;

import dev.pack.ttt.notion.service.DatabaseService;
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
