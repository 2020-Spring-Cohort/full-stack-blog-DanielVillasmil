package org.wcci.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

@Controller
@RequestMapping("post")
public class PostController {
    private AuthorStorage authorStorage;
    private CategoryStorage categoryStorage;
    private PostStorage postStorage;
    private TagStorage tagStorage;

    public PostController(PostStorage postStorage, AuthorStorage authorStorage, CategoryStorage categoryStorage, TagStorage tagStorage) {
        this.postStorage = postStorage;
        this.authorStorage = authorStorage;
        this.categoryStorage = categoryStorage;
        this.tagStorage = tagStorage;
    }

    @PostMapping("add")
    public String AddPostForm(@RequestParam("authors") String author, @RequestParam("category") String category, @RequestParam("postTitle") String postTitle, @RequestParam("PostBody") String postBody) {
    postStorage.store(new Post(author, category, postTitle, postBody));
    return "redirect:/post/all-posts";

    }
    @RequestMapping("/single-post/{id}")
    public String displayPost(@PathVariable Long id, Model model){
        Post retrievePost = postStorage.findPostById(id);
        model.addAttribute("Post", retrievePost);
        return "post";
    }
    @GetMapping("all-posts")
    public String viewAllPosts(Model model) {
        model.addAttribute("authors", authorStorage.getAll());
        model.addAttribute("categories", categoryStorage.getAll());
        model.addAttribute("tags",tagStorage.getAll());
        model.addAttribute("posts", postStorage.getAll());
        return "posts";

    }
    @GetMapping("/{id}")
    public String displayPostFromAuthorPage(long id, Model model) {
        Post retrievedPost = postStorage.findPostById(id);
        model.addAttribute("post",retrievedPost);
        model.addAttribute("tags",tagStorage.getAll());
        return "post";
    }
}
