package com.example.portfolioapp.service;

import lombok.RequiredArgsConstructor;  // lombokのRequiredArgsConstructorをインポート
import org.springframework.security.core.userdetails.UserDetails;  // Spring SecurityのUserDetailsをインポート
import org.springframework.security.core.userdetails.UserDetailsService;  // Spring SecurityのUserDetailsServiceをインポート
import org.springframework.security.core.userdetails.UsernameNotFoundException;  // ユーザーが見つからなかった場合の例外をインポート
import org.springframework.stereotype.Service;  // SpringのServiceアノテーションをインポート

import com.example.portfolioapp.authentication.CustomUserDetails;
import com.example.portfolioapp.repository.UserRepository;

import java.util.Collections;  // JavaのCollectionsクラスをインポート

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;  // UserRepositoryの依存を注入
	
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name)  // ユーザー名でユーザー情報を検索
                .map(  // Optional型からカスタムなユーザー情報を作成
                        user -> new CustomUserDetails(
                                user.getName(),
                                user.getPassword(),
                                Collections.emptyList()
                        )
                )
                .orElseThrow(  // ユーザーが見つからない場合は例外をスロー
                        () -> new UsernameNotFoundException(
                                "ユーザー情報の認証に失敗しました。 (name = '" + name + "')"
                        )
                );
    }


}
