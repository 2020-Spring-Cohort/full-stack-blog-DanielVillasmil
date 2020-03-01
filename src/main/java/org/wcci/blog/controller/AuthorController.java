package org.wcci.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.PostStorage;

@Controller
@RequestMapping("author")
public class AuthorController {

    private AuthorStorage authorStorage;
    private PostStorage postStorage;

    public AuthorController(AuthorStorage authorStorage){
        this.authorStorage = authorStorage;
    }
    @GetMapping("/{authorId}")
    public String displayAuthorsFromPage(@PathVariable long authorId, Model model){
        Author retrievedAuthor = authorStorage.findAuthorById(authorId);
        model.addAttribute("author", retrievedAuthor);
        return "author";
    }

    @PostMapping("add")
    public String addAuthorForm(@RequestParam String authorName){
        authorStorage.store(new Author(authorName));
        return "redirect:/author/all-authors";
    }
    @GetMapping("all-authors")
    public String viewAllAuthors(Model model){
        model.addAttribute("authors", authorStorage.getAll());
        return "authors";
    }
}
