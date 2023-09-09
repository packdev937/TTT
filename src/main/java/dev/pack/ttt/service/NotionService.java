package dev.pack.ttt.service;

import dev.pack.ttt.model.Content;
import dev.pack.ttt.model.Status;
import dev.pack.ttt.notion.NotionClient;
import dev.pack.ttt.notion.config.NotionConfigProperties;
import dev.pack.ttt.notion.model.Block;
import dev.pack.ttt.notion.model.Page;
import dev.pack.ttt.tistory.service.TistoryService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotionService {

    private final NotionClient notionClient;
    private final NotionConfigProperties notionConfigProperties;

    private static final Logger log = LoggerFactory.getLogger(NotionService.class);


    public Content mapPageToContent(Page page) {
        log.info("Call mapPageToContent()");
        if (page == null) {
            throw new RuntimeException("Properties in Page object is null");
        }
        return new Content(page.getParent().get("database_id").asText(), page.getId(),
            page.getProperties().get("Title").get("title").get(0).get("text")
                .get("content").asText(), LocalDate.parse(
            page.getProperties().get("Date").get("date").get("start").asText(),
            DateTimeFormatter.ISO_LOCAL_DATE),
            page.getProperties().get("Category").get("select").get("name").asText(), Status.valueOf(
            page.getProperties().get("Status").get("select").get("name").asText()));
    }


    public List<Post> convertBlockToPost() {
        log.info("Call convertBlockToPost()");
        // Initialization
        List<Post> posts = new ArrayList<>();
        List<Content> notCompletedPage = findUploadingPages();

        for (Content content : notCompletedPage) {
            List<Block> blocks = findAllBlock(content.pageId());

            Post post = Post.builder()
                .content(content)
                .blocks(blocks)
                .build();
            posts.add(post);
        }
        return posts;
    }

    public List<Page> findAllContent() {
        log.info("Call findAllContent()");
        return notionClient.databaseService.query(notionConfigProperties.databaseId());
    }

    public List<Block> findAllBlock(String pageId) {
        return notionClient.databaseService.block(pageId);
    }

    public List<Content> findUploadingPages() {
        log.info("Call findUploadingPages()");
        List<Content> pages = new ArrayList<>();
        for (Content content : findAllContent().stream().map(NotionService::mapPageToContent)
            .toList()) {
            if (content.status().equals(Status.UPLOADING)) {
                pages.add(content);
            }
        }
        return pages;
    }
}
