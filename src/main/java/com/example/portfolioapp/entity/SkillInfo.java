package com.example.portfolioapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
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
	private int month;
	
	//カテゴリーID
	@Column(name="category_id")
	private Long category_id;
	
	//ユーザーID
	private Long user_id;
	
	//登録日時
	private Date created_at;
	
	//更新日時
	private Date updated_at;
	
	
	
			@ManyToMany
		    @JoinTable(
		        name = "learning_data_category",
		        joinColumns = @JoinColumn(name = "learning_data_id"),
		        inverseJoinColumns = @JoinColumn(name = "category_id")
		    )
		    private Set<CategoriesInfo> categoriesInfo;
	

}
