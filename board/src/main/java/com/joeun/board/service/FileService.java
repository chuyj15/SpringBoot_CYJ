package com.joeun.board.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.joeun.board.dto.Files;


public interface FileService {
     //파일 목록
    public List<Files> list() throws Exception;
    //파일 조회
    public Files select(int FilesNo) throws Exception;
    //파일 등록
    public int insert(Files file) throws Exception;
    //파일 수정
    public int update(Files file) throws Exception;
    //파일 삭제
    public int delete(int fileNo) throws Exception;
    //파일목록 by parent
    public List<Files> listByParent(Files file) throws Exception;
    //파일삭제 by parent
    public int deleteByParent(Files file) throws Exception;
    //파일 다운로드
    public int download(int fileNo, HttpServletResponse response) throws Exception;
 
}
