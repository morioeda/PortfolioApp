package com.example.portfolioapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portfolioapp.dao.UserInfoMapper;
import com.example.portfolioapp.dto.SkillAddRequest;

@Service
public class SkillInfoService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public void add(SkillAddRequest skillAddRequest) {
		userInfoMapper.add(skillAddRequest);
		}

}
