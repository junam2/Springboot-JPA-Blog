package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) {
        // 로그인이 성공하면 session 을 찾아야 한다.
        System.out.println(principal.getUsername());

        // /WEB-INF/views/index.jsp
        return "index";
    }
}
