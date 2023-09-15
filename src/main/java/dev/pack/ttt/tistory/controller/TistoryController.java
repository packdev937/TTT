package dev.pack.ttt.tistory.controller;

import dev.pack.ttt.tistory.model.Post;
import dev.pack.ttt.notion.service.NotionService;
import dev.pack.ttt.tistory.service.TistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TistoryController {

    private final TistoryService tistoryService;
    private final NotionService notionService;

    @GetMapping("/upload")
    public String upload() {
        String response = "";
        List<Post> posts = notionService.convertBlockToPost();
        for (Post post : posts) {
            try {
                response = tistoryService.post(post);
            } catch (Exception e) {
                log.info("{}", e);
            } finally {
                notionService.updateNotionPageStatus(post.getContent().pageId());
            }
        }
        return response;
    }
}
