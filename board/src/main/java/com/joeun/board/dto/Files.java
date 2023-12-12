package com.joeun.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Files {
    
  private int fileNo;
  private String fileName;
  private int parentNo;
  private String filePath;
  private String parentTable;
  private String originName;
  private long fileSize; //너무 길수있으니까.. 롱타입으로 바꿀 예정
  private Date regDate;
  private Date updDate;
  private int fileCode;
  
}
