package com.example.portfolioapp.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import lombok.Data;

//ユーザー情報 Entity
@Table(name = "users")
@Entity
@Data
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
