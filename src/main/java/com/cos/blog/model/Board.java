package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content;

    @ColumnDefault("0") // 숫자이기 때문에 ''를 사용하지 않는다.
    private int count;

    @ManyToOne(fetch = FetchType.EAGER) // Board = Many, User = One
    @JoinColumn(name="userId")
    private User user; // User 테이블의 ID -> ORM 에서는 자바 오브젝트를 이용할 수 있다.

    // CascadeType.REMOVE -> board 를 지울 때 reply 도 함께 제거한다.
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다. (FK가 아니다.) DB에 컬럼을 만들지 않는다.
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replies;

    @CreationTimestamp
    private Timestamp createDate;
}
