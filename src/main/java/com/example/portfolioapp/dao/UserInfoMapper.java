package com.example.portfolioapp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.entity.UserInfo;
import org.springframework.security.core.userdetails.User;

@Mapper
public interface UserInfoMapper {
	
    /**
     * ユーザー情報登録
     * @param userRequest 登録用リクエストデータ
     */
     void save(UserAddRequest userRequest);
     
     public UserInfo findByName(String name);

}
