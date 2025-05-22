package com.kh.jpa.dto;

import com.kh.jpa.entity.*;
import com.kh.jpa.enums.CommonEnums;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

    //생성용
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Create{
        private String board_title;
        private String board_content;
        private String user_id;
        private MultipartFile file;
        private List<String> tags;

        public Board toEntity() {
            return Board.builder()
                    .boardTitle(this.board_title)
                    .boardContent(this.board_content)
                    .build();
        }
    }

    //조회용
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long board_no;
        private String board_title;
        private String board_content;
        private String origin_name;
        private String change_name;
        private LocalDateTime create_date;
        private Integer count;
        private String user_id;
        private String user_name;
        private List<String> tags;

        public static Response toSimpleDto(Board board) {
            return Response.builder()
                    .board_no(board.getBoardNo())
                    .board_title(board.getBoardTitle())
                    .origin_name(board.getOriginName())
                    .count(board.getCount())
                    .user_id(board.getMember().getUserId())
                    .create_date(board.getCreateDate())
                    .build();
        }

        public static Response toDto(Board board) {
            return Response.builder()
                    .board_no(board.getBoardNo())
                    .board_title(board.getBoardTitle())
                    .board_content(board.getBoardContent())
                    .origin_name(board.getOriginName())
                    .change_name(board.getChangeName())
                    .count(board.getCount())
                    .create_date(board.getCreateDate())
                    .user_id(board.getMember().getUserId())
                    .user_name(board.getMember().getUserName())
                    .tags(board.getBoardTags()
                                    .stream()
                                    .map(boardTag -> boardTag.getTag().getTagName())
                                    .toList())
                    .build();

        }
    }

    //수정용
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{

        private String board_title;
        private String board_content;
        private MultipartFile file;
        private List<String> tags;

    }
}
