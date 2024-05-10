package com.example.portfolioapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.portfolioapp.dao.UserInfoMapper;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.entity.UserInfo;

@Service
public class UserInfoService {
	
    @Autowired
    private UserInfoMapper userInfoMapper;
	
    /**
     * ユーザ情報登録
     * @param userAddRequest リクエストデータ
     */
    public void save(UserAddRequest userAddRequest) {
        userInfoMapper.save(userAddRequest);
        
        }
        
    }
	

