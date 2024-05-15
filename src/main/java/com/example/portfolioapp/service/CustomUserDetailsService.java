package com.example.portfolioapp.service;

import lombok.RequiredArgsConstructor;  // lombokのRequiredArgsConstructorをインポート

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;  // Spring SecurityのUserDetailsをインポート
import org.springframework.security.core.userdetails.UserDetailsService;  // Spring SecurityのUserDetailsServiceをインポート
import org.springframework.security.core.userdetails.UsernameNotFoundException;  // ユーザーが見つからなかった場合の例外をインポート
import org.springframework.stereotype.Service;  // SpringのServiceアノテーションをインポート

import com.example.portfolioapp.authentication.CustomUserDetails;
import com.example.portfolioapp.entity.UserInfo;
import com.example.portfolioapp.repository.UserRepository;

import java.util.Collections;  // JavaのCollectionsクラスをインポート

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;  // UserRepositoryの依存を注入

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // DBベースのユーザー検索
        UserInfo userInfo = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        return User.builder()
                .username(userInfo.getEmail()) // ユーザー名としてメールアドレスを使用
                .password(userInfo.getPassword()) // ハッシュ化されたパスワード
                .build();
        }


}