package dev.pack.ttt.notion.controller;

import dev.pack.ttt.model.Content;
import dev.pack.ttt.notion.model.Page;
import dev.pack.ttt.service.NotionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotionController {

    private final NotionService notionService;

    // pages
    @GetMapping("/pages")
    public List<Page> findAllPages() {
        return notionService.findAllContent();
    }
}
