package dev.pack.ttt.tistory.service;

import dev.pack.ttt.model.Post;
import dev.pack.ttt.tistory.config.TistoryConfigProperties;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class TistoryService {

    private final RestTemplate restTemplate;
    private final TistoryConfigProperties tistoryConfigProperties;

    public String post(Post post) {
        String url = tistoryConfigProperties.apiUrl()
            + "/post/write?access_token=" + tistoryConfigProperties.accessToken()
            + "&output=html"
            + "&blogName=" + tistoryConfigProperties.blogName()
            + "&title=" + post.getContent().title()
            + "&content=" + post.getHtml()
            + "&visibility=" + post.getVisibility()
            + "&category=" + post.getCategory()
            + "&published=" + "2023-09-06"
            + "&slogan=" + post.getSlogan()
            + "&tag=" + post.getTag()
            // 댓글 허용
            + "&acceptComment=0"
            + "&password=1234";

        ResponseEntity<String> db = restTemplate.exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<>("", getDefaultHeaders()),
            new ParameterizedTypeReference<String>() {
            }
        );
        // 헤더 확인
        log.info("Response Header {}", db.getHeaders());

        return db.getBody();
    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
