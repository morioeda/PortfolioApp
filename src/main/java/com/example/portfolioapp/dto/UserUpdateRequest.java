package com.example.portfolioapp.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserUpdateRequest implements Serializable{
	
	
	//IDの追加
	private Long id;
	
	//自己紹介文
	@NotEmpty
	@Size(min = 50,max = 200, message = "50文字以上、200文字以下で入力してください")
    private String self_introduction;
	
	
	public String getSelfIntroduction() { //ゲッターメソッドを定義
    	return self_introduction;
    }
	
	public void setSelfIntroduction(String self_introduction) {
		this.self_introduction = self_introduction; 
	}
}