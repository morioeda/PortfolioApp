package com.example.portfolioapp.dao;

import org.apache.ibatis.annotations.Mapper;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
	
    /**
     * ユーザー情報登録
     * @param userRequest 登録用リクエストデータ
     */
     void save(UserAddRequest userRequest);

}
