package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을 해줌. IoC 를 해준다.
@Service
public class UserService {

    /*
    서비스를 사용하는 이유.

    1. 트랜잭션 관리

    2. 서비스 의미
    - repository 의 CRUD 의 여러개 트랜잭션을 하나의 트랜잭션으로 만들어서 서비스로 사용 가능하다.
    */

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public Integer Join(User user) {
        try {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            user.setPassword(encPassword);
            user.setRole(RoleType.USER);
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserService : Join() : " + e.getMessage());
        }

        return -1;
    }

//    @Transactional(readOnly = true)
//    select 할 때 트랜잭션 시작, 해당 서비스가 종료 될때 트랜잭션이 종료 정합성을 유지
//    public User Login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//
//    }
}
