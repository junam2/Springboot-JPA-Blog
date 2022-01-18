package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @DynamicInsert // insert 시 null인 컬럼을 제외해준다.
@Entity // User 클래스가 MySQL에 테이블이 자동으로 생성된다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, length = 100) // 해쉬를 통한 암호화 필요
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'")
//    private String role; // admin, user, manager 권한

    //DB는 roletypedl 이 없어 String임을 알려주는게 좋다.
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp // 시간 자동 입력 ( now() )
    private Timestamp createDate;
}
