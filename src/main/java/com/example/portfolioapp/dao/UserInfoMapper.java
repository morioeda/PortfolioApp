package com.example.portfolioapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.portfolioapp.dto.SkillAddRequest;
import com.example.portfolioapp.dto.StudyTimeUpdateRequest;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.dto.UserUpdateRequest;
import com.example.portfolioapp.entity.CategoriesInfo;
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
     List <SkillInfo> findAll(Long userId);
     
     //項目名検索（重複チェック用）
     public SkillInfo findName(String name);
     
	//カテゴリー名検索（カテゴリー名表示用）※SkillInfoじゃないやつ作成した方がいいかも
	public CategoriesInfo findCategory(Long category_id);
	
	//学習時間の更新
	void updateTime(StudyTimeUpdateRequest studyTimeUpdateRequest);
	
	//学習データの削除※削除対象のIDを引数として受け取る
	void deleteData(Long id);
     
	//学習時間合計の表示
	List <SkillInfo> SumStudyTime(Long userId);
}
