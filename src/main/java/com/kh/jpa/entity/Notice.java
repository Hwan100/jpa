package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notice {

    @Id
    @Column(name = "notice_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeNo;

    @Column(name = "notice_title", nullable = false, length = 30)
    private String noticeTitle;

    @Column(name = "notice_content", nullable = false, length = 30)
    private String noticeContent;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_writer", nullable = false)
    private Member member = new Member();
}
