package com.example.portfolioapp.dao;

import org.apache.ibatis.annotations.Mapper;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.dto.UserUpdateRequest;
import com.example.portfolioapp.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
	
    /**
     * ユーザー情報登録
     * @param userRequest 登録用リクエストデータ
     */
     void save(UserAddRequest userRequest);
     
     //ユーザー検索
     public UserInfo findByEmail(String email);
     
     //自己紹介文追加
     void update(UserUpdateRequest userUpdateRequest);

}
