package dev.pack.ttt.tistory.service;

import dev.pack.ttt.model.Post;
import dev.pack.ttt.tistory.config.TistoryConfigProperties;
import java.time.LocalDate;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class TistoryService {

    private final RestTemplate restTemplate;
    private final TistoryConfigProperties tistoryConfigProperties;

    public String post(Post post) {
        String url = tistoryConfigProperties.apiUrl() + "/post/write";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("access_token", tistoryConfigProperties.accessToken());
        body.add("output", "html");
        body.add("blogName", tistoryConfigProperties.blogName());
        body.add("title", post.getContent().title());
        body.add("content", post.getHtml().toString());
        body.add("visibility", String.valueOf(post.getVisibility()));
        body.add("category", String.valueOf(post.getCategory()));
        body.add("published", String.valueOf(LocalDate.now()));
        body.add("slogan", post.getSlogan());
        body.add("tag", post.getTag());
        body.add("acceptComment", "1");
        body.add("password", "1234");

        HttpHeaders headers = getDefaultHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        return response.getBody();
    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
