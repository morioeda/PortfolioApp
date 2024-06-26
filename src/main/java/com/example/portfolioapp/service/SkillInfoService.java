package com.example.portfolioapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portfolioapp.dao.UserInfoMapper;
import com.example.portfolioapp.dto.SkillAddRequest;
import com.example.portfolioapp.dto.StudyTimeUpdateRequest;
import com.example.portfolioapp.dto.UserUpdateRequest;
import com.example.portfolioapp.entity.CategoriesInfo;
import com.example.portfolioapp.entity.SkillInfo;

@Service
public class SkillInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	//学習データの表示
	public List<SkillInfo>findAll(Long userId){
		return userInfoMapper.findAll(userId);
	}	
	
	//項目追加
	public void add(SkillAddRequest skillAddRequest) {
		userInfoMapper.add(skillAddRequest);
		}
	
	//項目名がDB内に既に存在しているかを確認する
	public boolean isItemExist(String name,Long userId) {
		
		SkillInfo existingItem = userInfoMapper.findName(name,userId);
		return existingItem != null;
	}

	//カテゴリー名の検索
	public CategoriesInfo findCategory(Long category_id) {
		return userInfoMapper.findCategory(category_id);
	}
	
	//学習時間の更新
	public void update(StudyTimeUpdateRequest studyTimeUpdateRequest) {
		userInfoMapper.updateTime(studyTimeUpdateRequest);
		}
	
	//学習データの削除
	public void deleteData(Long id) {
		userInfoMapper.deleteData(id);
	}
	
	//学習時間合計の表示
	public List<SkillInfo>SumStudyTime(Long userId){
		return userInfoMapper.SumStudyTime(userId);
	}	
}
