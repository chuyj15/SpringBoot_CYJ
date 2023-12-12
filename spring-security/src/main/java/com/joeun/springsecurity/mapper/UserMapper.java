package com.joeun.springsecurity.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.joeun.springsecurity.dto.UserAuth;
import com.joeun.springsecurity.dto.Users;

@Mapper
public interface UserMapper {

    //회원 등록
    public int insert(Users user) throws Exception;

    //회원 조회
    public Users select(int userNo) throws Exception;

    //회원권한 등록
    public int insertAuth(UserAuth userAuth) throws Exception;

    //사용자인증(로그인) 
    public Users login(String username);

}
