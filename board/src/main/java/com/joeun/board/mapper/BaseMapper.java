package com.joeun.board.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.board.dto.Files;


@Mapper
public interface BaseMapper {
    //게시글 목록
    public List<Files> list() throws Exception;
    //게시글 조회
    public Files select(int baseNo) throws Exception;
    //게시글 등록
    public int insert(Files base) throws Exception;
    //게시글 수정
    public int update(Files base) throws Exception;
    //게시글 삭제
    public int delete(int baseNo) throws Exception;

    //게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;
}
