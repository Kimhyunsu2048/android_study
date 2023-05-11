package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Diary {

    @Id @GeneratedValue
    private Long id; // 등록번호

    @Column(length=100, nullable = false)
    private String subject; // 제목 

    @Lob
    @Column(nullable = false)
    private String content; // 내용

    @ManyToOne
    @JoinColumn(name="userNo")
    private Users user; // 작성자

    private String imageUrl; // 이미지 경로

    @CreatedDate
    private LocalDateTime regDt; // 작성일시

    @LastModifiedDate
    private LocalDateTime modDt; // 수정일시
}
