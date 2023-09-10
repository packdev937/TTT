package dev.pack.ttt.tistory.service;

import dev.pack.ttt.model.Post;
import org.springframework.stereotype.Service;

@Service
public class TistoryService {

    public Post upload(Post post) {

        return db.getBody();
    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
