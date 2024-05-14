package com.example.portfolioapp.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.AllArgsConstructor;

//ユーザー情報 Entity
@Entity
@Data
@AllArgsConstructor
public class UserInfo implements Serializable{
	
	
	//ID
	@Id
    private Long id;

    //メールアドレス
    private String email;
    
    //名前
    private String name;

    //自己紹介
    private String self_introduction = "自己紹介を追加してください";

    //パスワード
    private String password;

     //登録日時
    private Date created_at;

    //更新日時
    private Date updated_at;
	

}
