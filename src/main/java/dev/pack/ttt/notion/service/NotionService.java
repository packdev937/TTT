package dev.pack.ttt.notion.service;

import dev.pack.ttt.notion.model.Content;
import dev.pack.ttt.tistory.model.Post;
import dev.pack.ttt.notion.model.Status;
import dev.pack.ttt.notion.NotionClient;
import dev.pack.ttt.notion.config.NotionConfigProperties;
import dev.pack.ttt.notion.model.Block;
import dev.pack.ttt.notion.model.Page;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotionService {

    private final NotionClient notionClient;
    private final NotionConfigProperties notionConfigProperties;

    // Domain Service로 내려야 될 듯
    public Content mapPageToContent(Page page) {
        log.info("Call mapPageToContent()");
        if (page == null) {
            throw new RuntimeException("Properties in Page object is null");
        }

        // 디미터 법칙 위반
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
        List<Content> notCompletedPage = findAllPagesWithUploadingStatus();

        for (Content content : notCompletedPage) {
            List<Block> blocks = findAllBlocks(content.pageId());

            Post post = new Post(content, blocks);
            posts.add(post);
        }
        return posts;
    }

    public List<Page> findAllContents() {
        log.info("Call findAllContent()");
        return notionClient.databaseService.query(notionConfigProperties.databaseId());
    }

    public List<Block> findAllBlocks(String pageId) {
        return notionClient.databaseService.block(pageId);
    }

    public List<Content> findAllPagesWithUploadingStatus() {
        log.info("Call findUploadingPages()");
        List<Content> pages = new ArrayList<>();
        for (Page page : findAllContents()) {
            if (page.getProperties().get("Status").get("select").get("name").asText()
                .equals(String.valueOf(Status.UPLOADING))) {
                pages.add(mapPageToContent(page));
            }
        }
        return pages;
    }

    public String updateNotionPageStatus(String pageId) {
        return notionClient.databaseService.patch(pageId);
    }
}
