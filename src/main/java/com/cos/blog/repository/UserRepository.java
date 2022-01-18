package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO -> jsp
// 자동으로 bean 등록이 된다. (@Repository 생략 가능)
// User 테이블을 관리하는 repo 이고 pk는 integer 이다.
public interface UserRepository extends JpaRepository<User, Integer> {
    // JPA Naming 쿼리
    // select * from user where username= ? and password = ? 로 자동으로 된다.
    User findByUsernameAndPassword(String username, String password);

    // 두 번째 방법
//    @Query(value="SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
//    User login(String username, String password);
}
