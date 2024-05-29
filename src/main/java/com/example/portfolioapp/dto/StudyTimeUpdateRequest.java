package com.example.portfolioapp.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class StudyTimeUpdateRequest implements Serializable{
	
	//IDの追加
	private Long id;
	
	private int study_time;

}
