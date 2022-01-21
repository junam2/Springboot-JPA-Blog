package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO -> jsp
// 자동으로 bean 등록이 된다. (@Repository 생략 가능)
// User 테이블을 관리하는 repo 이고 pk는 integer 이다.
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
