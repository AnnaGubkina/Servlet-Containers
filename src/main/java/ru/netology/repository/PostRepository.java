package ru.netology.repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private final Map<Long, Post> allPosts;
    private final AtomicLong idCounter = new AtomicLong();

    public PostRepository() {
        this.allPosts = new ConcurrentHashMap<>();
    }

    public Collection<Post> all() {
        return allPosts.values();
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(allPosts.get(id));
    }

    public Optional<Post> save(Post savePost) {
        if (savePost.getId() == 0) {
            long id = idCounter.incrementAndGet();
            savePost.setId(id);
            allPosts.put(id, savePost);
        } else if (savePost.getId() != 0) {
            Long currentId = savePost.getId();
            allPosts.put(currentId, savePost);
        }
        return Optional.of(savePost);
    }

    public void removeById(long id)  {
        if (allPosts.containsKey(id)) {
            allPosts.remove(id);
        } else {
            throw new NotFoundException("Введен неверный id");
        }
    }
}
