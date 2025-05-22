package com.kh.jpa.entity;

import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Board {

    @Id
    @Column(name = "board_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    @Column(name = "board_title", nullable = false, length = 100)
    private String boardTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_writer", nullable = false)
    private Member member;

    public void changeMember(Member member) {
        this.member = member;
        if (member.getBoards().contains(this)) {
            member.getBoards().add(this);
        }
    }

    @Lob // 대용량 데이터 맵핑
    @Column(name = "board_content", nullable = false)
    private String boardContent;

    @Column(name = "origin_name", length = 100)
    private String originName;

    @Column(name = "change_name", length = 100)
    private String changeName;

    @Column
    private Integer count;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private CommonEnums.Status status;

    @PrePersist
    public void prePersist() {
        this.count = 0;
        this.createDate = LocalDateTime.now();
        this.status = CommonEnums.Status.Y;
    }

    @Builder.Default
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    //BoardTag : Board ( N:1 )
    //orphanRemoval = true N : 1 또는 1 : N 연관관계에서 자식 생명주기를 부모가 완전히 통제하겠다.
    //부모 엔티티에서 자식과의 관계가 제거되면, 자식도 자동으로 삭제
    @Builder.Default
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardTag> boardTags = new ArrayList<>();

    public void changeFile(String originName, String changeName) {
        this.originName = originName;
        this.changeName = changeName;
    }

    public void changeTitle(String updateBoardTitle) {
        if(updateBoardTitle != null && !updateBoardTitle.isEmpty()) {
            this.boardTitle = updateBoardTitle;
        }


    }

    public void changeContent(String updateBoardContent) {
        if(updateBoardContent != null && !updateBoardContent.isEmpty()) {
            this.boardContent = updateBoardContent;
        }
    }

}
