package com.example.portfolioapp.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "categories")
@Entity
@Data
public class CategoriesInfo {
	
	//ID
	@Id
	private Long id;
	
	//項目名
	private String name;
	
    //登録日時
   private Date created_at;

   //更新日時
   private Date updated_at;
	
   
   @ManyToMany(mappedBy = "categoriesInfo")
   private Set<SkillInfo> skillInfo;

}
