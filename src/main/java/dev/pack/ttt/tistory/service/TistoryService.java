package dev.pack.ttt.tistory.service;

import dev.pack.ttt.model.Post;
import org.springframework.stereotype.Service;

@Service
public class TistoryService {

    public Post upload(Post post) {

        return Post.builder().build();
    }
}
