package com.example.chatDemo.web;

import com.example.chatDemo.config.auth.LoginUser;
import com.example.chatDemo.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) //서버 템플릿 엔진에서 사용할 수 있는 객체 저장
    {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

}
