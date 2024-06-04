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
    @NotEmpty(message = "名前は必ず入力してください")
    @Size(max = 100, message = "名前は255文字以内で入力してください")
    private String name;
    
     //メールアドレス
    @Email(message = "メールアドレスが正しい形式ではありません")
    private String email;
    
    // 自己紹介
    @Size(min = 50,max = 200, message = "50文字以上、200文字以下で入力してください")
    private String self_introduction;

    
     //パスワード
    @NotEmpty(message = "パスワードは必ず入力してください")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{8,}$", message = "英数字8文字以上で入力してください")
    private String password;
    
 
 
    
}