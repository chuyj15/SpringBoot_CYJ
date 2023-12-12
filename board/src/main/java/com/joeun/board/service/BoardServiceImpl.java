package com.joeun.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Files;
import com.joeun.board.mapper.BoardMapper;
import com.joeun.board.mapper.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileMapper fileMapper;
    //quick fix : ctrl + .
   
    @Value("${upload.path}") //application.properties에 설정한 업로드경로 속성명
    private String uploadPath;  //업로드 경로

    @Override
    public List<Board> list() throws Exception {
        return boardMapper.list();
    }

    @Override
    public Board select(int BoardNo) throws Exception {
        //조회수 오르는 기능
        return boardMapper.select(BoardNo);
    }

    @Override
    public int insert(Board board) throws Exception {
        
        int result = boardMapper.insert(board);

        //파일 업로드
        List<MultipartFile> fileList = board.getFile();
        if ( ! fileList.isEmpty() ){
            for (MultipartFile file : fileList ){
                //파일 정보 : 원본파일명, 파일용량, 파일데이터
                String originName = file.getOriginalFilename();
                long fileSize = file.getSize();
                byte[] fileData = file.getBytes();
                int parentNo = boardMapper.maxPk();
                log.info(parentNo +"");
                String parentTable = "board";

                //업로드 경로
                //파일명 중복 방지
                String fileName= UUID.randomUUID().toString()+"_"+originName;
                String filePath = uploadPath+"/"+fileName;
                
                //파일업로드
                //1. 서버측, 파일시스템에 파일 복사
                //2. DB에 파일 정보 등록
                File uploadFile = new File(uploadPath, fileName);
                FileCopyUtils.copy(fileData, uploadFile);

            // FileOutputStream fos = new FileOutputStream(uploadFile);
            // fos.write(fileData);
            // fos.close();
            
            Files uploadedFile = new Files();
            uploadedFile.setParentTable(parentTable);
            uploadedFile.setParentNo(parentNo);
            uploadedFile.setFileName(fileName);
            uploadedFile.setFilePath(filePath);
            uploadedFile.setOriginName(originName);
            uploadedFile.setFileSize(fileSize);
            uploadedFile.setFileCode(0);
            fileMapper.insert(uploadedFile);
            }
        }
        return result;
        
    }

    @Override
    public int update(Board board) throws Exception {
        return boardMapper.update(board);
    }

    @Override
    public int delete(int boardNo) throws Exception {
        return boardMapper.delete(boardNo);
    }
    
}
