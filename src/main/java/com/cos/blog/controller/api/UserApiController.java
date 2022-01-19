package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        int result = userService.Join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
    }

    /*
    - 전통적인 로그인 방식

    @Autowired
    private HttpSession session;

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user) {
        User principal = userService.Login(user);

        if(principal != null) {
            session.setAttribute("principal", principal);
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
     */
}
