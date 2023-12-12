package com.joeun.board.service;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.joeun.board.dto.Files;
import com.joeun.board.mapper.FileMapper;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<Files> list() throws Exception {
        List<Files> fileList = fileMapper.list();
        return fileList;
    }

    @Override
    public Files select(int FilesNo) throws Exception {
        Files file = fileMapper.select(FilesNo);
        return file;
    }

    @Override
    public int insert(Files file) throws Exception {
        return fileMapper.insert(file);
    }

    @Override
    public int update(Files file) throws Exception {
        return fileMapper.update(file);
    }

    @Override
    public int delete(int fileNo) throws Exception {
        return fileMapper.delete(fileNo);
    }

    @Override
    public List<Files> listByParent(Files file) throws Exception {
        return fileMapper.listByParent(file);
    }

    @Override
    public int deleteByParent(Files file) throws Exception {
        return fileMapper.deleteByParent(file);
    }

    @Override
    public int download(int fileNo, HttpServletResponse response) throws Exception {
        Files file = fileMapper.select(fileNo);

        if( file == null ) {
            // BAD_REQUEST : 400, 클라이언트의 요청이 잘못되었음을 알려주는 상태코드
            response.setStatus(response.SC_BAD_REQUEST);
            return 0;
        }

        String filePath = file.getFilePath();
        String fileName = file.getFileName();

        // 다운로드 응답을 위한 헤더 세팅
        // - ContentType            : application/octet-stream
        // - Content-Disposition    : attachment, filename="파일명.확장자"
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 파일 다운로드
        // - 파일 입력
        //File downloadFile = new File(filePath);
      //  FileInputStream fis = new FileInputStream( new File(filePath) );

        // - 파일 출력
       // ServletOutputStream sos = response.getOutputStream();

        // 다운로드
        FileCopyUtils.copy(  new FileInputStream( new File(filePath) ) , response.getOutputStream() );

        // byte[] buffer = new byte[1024];             // 1024bytes = 1KB 단위 버퍼
        // int data;
        // while( (data = fis.read(buffer)) != -1 ) {  // 1KB 씩 파일입력
        //     sos.write(buffer, 0, data);         // 1KB 씩 파일출력
        // }
        // fis.close();
        // sos.close();

        //수정해야함
       return  1;

    }

   
    
}
