package com.example.portfolioapp.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//ユーザー情報 Entity
@Table(name = "learning_data")
@Entity
@Data
public class SkillInfo implements Serializable{
	
	//ID
	@Id
	private Long id;
	
	//項目名
	private String name;
	
	//学習時間
	private int study_time;
	
	//月
	private Date month;
	
	//カテゴリーID
	private Long category_id;
	
	//ユーザーID
	private Long user_id;
	
	//登録日時
	private Date created_at;
	
	//更新日時
	private Date updated_at;
	

}
