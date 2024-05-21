package com.example.portfolioapp.service;

import lombok.RequiredArgsConstructor;  // lombokのRequiredArgsConstructorをインポート

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;  // Spring SecurityのUserDetailsをインポート
import org.springframework.security.core.userdetails.UserDetailsService;  // Spring SecurityのUserDetailsServiceをインポート
import org.springframework.security.core.userdetails.UsernameNotFoundException;  // ユーザーが見つからなかった場合の例外をインポート
import org.springframework.stereotype.Service;  // SpringのServiceアノテーションをインポート

import com.example.portfolioapp.authentication.CustomUserDetails;
import com.example.portfolioapp.entity.UserInfo;
import com.example.portfolioapp.repository.UserRepository;

import java.util.Collection;
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
        
     // 権限を設定（ここでは簡単のため一つの権限のみ設定）
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        
        //UserInfoオブジェクトのデータを利用してUserDetailsオブジェクトを作成。ユーザー認証に使用
        
        return new CustomUserDetails(userInfo.getEmail(), userInfo.getPassword(), authorities, userInfo.getName());
    }

}