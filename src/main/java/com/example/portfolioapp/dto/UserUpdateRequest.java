package com.example.portfolioapp.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserUpdateRequest implements Serializable{
	
	//自己紹介文
	@NotEmpty
	@Size(min = 50,max = 200, message = "50文字以上、200文字以下で入力してください")
    private String self_introduction;
	
}
