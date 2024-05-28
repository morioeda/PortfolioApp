package com.example.portfolioapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portfolioapp.dao.UserInfoMapper;
import com.example.portfolioapp.dto.SkillAddRequest;
import com.example.portfolioapp.entity.SkillInfo;

@Service
public class SkillInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	//学習データの表示
	public List<SkillInfo>findAll(){
		return userInfoMapper.findAll();
	}	
	
	//項目追加
	public void add(SkillAddRequest skillAddRequest) {
		userInfoMapper.add(skillAddRequest);
		}
		
	public boolean isItemExist(String name) {
		
		SkillInfo existingItem = userInfoMapper.findName(name);
		return existingItem != null;
	}


}
