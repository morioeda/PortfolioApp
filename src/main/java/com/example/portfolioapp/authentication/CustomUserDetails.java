package com.example.portfolioapp.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;  // Spring SecurityのUserクラスをインポート

import com.example.portfolioapp.entity.UserInfo;

import java.util.Collection;

public class CustomUserDetails extends User {  // CustomUserDetailsクラスがUserクラスを拡張
	
	private String name;//名前のフィールドを追加
	private Long id;

		
    public CustomUserDetails(String email, String password, Collection<? extends GrantedAuthority> authorities,String name,Long id) {
        super(email, password, authorities);  // 親クラスのコンストラクタを呼び出してインスタンスを初期化
        this.name = name;//フィールドの初期化
        this.id = id;
      
    }
    
    public String getName() { //ゲッターメソッドを定義
    	return name;
    }
    
    public Long getId() {
    	return id;
    }
    
     
}
