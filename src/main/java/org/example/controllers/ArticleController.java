package org.example.controllers;

import jakarta.validation.Valid;
import org.example.models.dto.ArticleDTO;
import org.example.models.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleControllers {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String renderIndex() {
        // Později zde budeme řešit předávání článků šabloně

        return "pages/articles/index";
    }
    @GetMapping("create")
    public String renderCreateForm(@ModelAttribute
                                   ArticleDTO article) {
        return "pages/articles/create";
    }
    @PostMapping("create")
    public String createArticle(
            @Valid @ModelAttribute ArticleDTO article,
            BindingResult result
    ) {
        if (result.hasErrors())
            return renderCreateForm(article);

       articleService.create(article);

        return "redirect:/articles";
    }
}
