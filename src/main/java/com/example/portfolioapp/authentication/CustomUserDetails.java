package com.example.portfolioapp.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;  // Spring SecurityのUserクラスをインポート
import java.util.Collection;

public class CustomUserDetails extends User {  // CustomUserDetailsクラスがUserクラスを拡張
    public CustomUserDetails(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);  // 親クラスのコンストラクタを呼び出してインスタンスを初期化
    }
}
