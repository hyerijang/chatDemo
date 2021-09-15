package com.example.commerceDemo.web;

import com.example.commerceDemo.common.config.auth.LoginUser;
import com.example.commerceDemo.common.config.auth.dto.SessionUser;
import com.example.commerceDemo.domains.catalog.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ItemService itemsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/catalog")
    public String catalog(Model model, @LoginUser SessionUser user) {
        model.addAttribute("items", itemsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "catalog";
    }


    @GetMapping("/items/new")
    public String itemsSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "items-save";
    }

}
