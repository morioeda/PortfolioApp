package com.example.portfolioapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.portfolioapp.dao.UserInfoMapper;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.entity.UserInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserInfoService {
	
    @Autowired
    private UserInfoMapper userInfoMapper;
    
    @Autowired
    PasswordEncoder passwordEncoder;
	
    /**
     * ユーザ情報登録
     * @param userAddRequest リクエストデータ
     * userAddRequestからパスワード取得
     * PasswordEncoderを使用して、取得したパスワードを安全な形式でエンコード
     *エンコードされたパスワードをuserAddRequestオブジェクトに設定
     *UsersMapperを使用して、エンコードされたパスワードを含むuserAddRequestオブジェクトをデータベースに保存します。
     */
    public void save(UserAddRequest userAddRequest) {
        String encodedPassword = passwordEncoder.encode(userAddRequest.getPassword());
        userAddRequest.setPassword(encodedPassword);
        userInfoMapper.save(userAddRequest);
    }
}
