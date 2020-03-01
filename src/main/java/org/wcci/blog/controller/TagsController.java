package org.wcci.blog.controller;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.TagStorage;

@Controller
@RequestMapping("tag")
public class TagsController {

    private TagStorage tagStorage;

    public TagsController(TagStorage tagStorage){
        this.tagStorage = tagStorage;
    }

    @GetMapping("/{tagId")
    public String displaySingleTag(@PathVariable long tagId, Model model){
        Tag retrievedTag = tagStorage.findTagById(tagId);
        model.addAttribute("tag", retrievedTag);
        return "tag";
    }
    @PostMapping("add")
    public String addTagForm(@RequestParam String tagName){
        tagStorage.add(new Tag(tagName));
        return "redirect:/tag/all-tags";
    }
    @GetMapping("allTags")
    public String viewAllTags(Model model){
        model.addAttribute("tags", tagStorage.getAll());
        return "tags";
    }



}
