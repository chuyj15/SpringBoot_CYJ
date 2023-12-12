package com.joeun.board.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.board.dto.Files;
import com.joeun.board.service.FileService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(value="/{fileNo}")
    public void fileDownload( @PathVariable("fileNo") int fileNo
                             ,HttpServletResponse response  ) throws Exception {

        int result = fileService.download(fileNo, response);
        //result : 0 - 파일 다운로드 처리 실패 , 1 - 파일 다운로드 성공
    }


    @DeleteMapping("")
    // public ResponseEntity<String> deleteFile(@RequestBody Files file) throws Exception {
    public ResponseEntity<String> deleteFile(Files file) throws Exception {
        log.info("[DELETE] - /file");
        int fileNo = file.getFileNo();
        log.info("fileNo : " + fileNo);
        if( fileNo == 0 )
            return new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);       

        int result = fileService.delete(fileNo);

        if( result == 0 )
            return new ResponseEntity<String>("FAIL", HttpStatus.OK);    
        
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
    
    
}