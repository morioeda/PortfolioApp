package com.example.portfolioapp.dto;

import java.io.Serializable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;



/**
 * ユーザー情報登録 リクエストデータ
 */
@Data
public class UserAddRequest implements Serializable {

     //名前
    @NotEmpty(message = "名前を入力してください")
    @Size(max = 100, message = "名前は100字以内で入力してください")
    private String name;

     //メールアドレス
    @Email
    private String email;
    
    // 自己紹介
    @Size(max = 200, message = "自己紹介は200文字以内で入力してください")
    private String self_introduction;

    
     //パスワード
    @NotEmpty(message = "パスワードを入力してください")
    @Size(min = 8, message = "パスワードは8字以上で入力してください")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]+$", message = "パスワードは数字と文字を含む必要があります")
    private String password;
    
 
 
    
}