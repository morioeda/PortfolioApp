package com.example.portfolioapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.portfolioapp.dto.SkillAddRequest;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.dto.UserUpdateRequest;
import com.example.portfolioapp.entity.SkillInfo;
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
     
     //自己紹介文更新
     void update(UserUpdateRequest userUpdateRequest);
     
     
     //学習時間追加
     void add(SkillAddRequest skillAddRequest);
     
     //学習データ検索
     List <SkillInfo> findAll();
     
     //項目名検索
     public SkillInfo findName(String name);
     
		//カテゴリー名検索
	public SkillInfo findCategory(Long category_id);
     
}
