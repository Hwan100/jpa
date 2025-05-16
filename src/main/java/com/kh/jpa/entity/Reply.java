package com.kh.jpa.entity;

import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply {

    @Id
    @Column(name = "reply_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNo;

    @Column(name = "reply_content", nullable = false, length = 400)
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ref_bno", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reply_writer", nullable = false)
    private Member member;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonEnums.Status status;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
        this.status = CommonEnums.Status.Y;
    }

}
