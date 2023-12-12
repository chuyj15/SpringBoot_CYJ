package com.joeun.springsecurity.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {
    private int userNo;
    private String userId;
    private String userPw;  //암호화된 비번
    private String userPwCheck; //평문의 비밀번호
    private String name;
    private String email;
    private Date regDate;
    private Date updDate;
    private int enabled; //휴먼 여부

    //권한 목록
    List<UserAuth> authList;
}
