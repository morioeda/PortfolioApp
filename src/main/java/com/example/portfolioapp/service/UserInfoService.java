package com.example.portfolioapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.portfolioapp.dao.UserInfoMapper;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.dto.UserUpdateRequest;
import com.example.portfolioapp.repository.UserRepository;
import com.example.portfolioapp.entity.UserInfo;


@Service
public class UserInfoService {
	
	//DBに対する操作を行うためのマッパークラス。これを使って、DBにデータを保存。
    @Autowired
    private UserInfoMapper userInfoMapper;
    
    @Autowired
    private UserRepository userRepository;
    
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
    
	/*    //自己紹介文の追加
	public void update(UserUpdateRequest userUpdateRequest) {
		userInfoMapper.update(userUpdateRequest);
	}*/
    
    public void update(String email, String selfIntroduction) {
        UserInfo userInfo = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        userInfo.setSelf_introduction(selfIntroduction);
        userRepository.save(userInfo);
        
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
		userInfoMapper.update(userUpdateRequest);
    }
    
}
