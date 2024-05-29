package com.example.portfolioapp.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 学習チャート情報登録 リクエストデータ
 */
@Data
public class SkillAddRequest implements Serializable{
	
	//ID
	private Long category_id;
	
	//ユーザーID
	private Long user_id;
	
	//項目名
	@NotEmpty(message = "項目名は必ず入力してください")
//	@UniqueElements(message = "（入力した項目名）は既に登録されています") 
	@Size(max = 50,message ="項目名は50文字以内で入力してください")
	private String name;
	
	//学習時間
	@NotNull(message = "学習時間は必ず入力してください")
	@Positive(message = "学習時間は1以上の数字で入力してください")
	 private int study_time;
}
