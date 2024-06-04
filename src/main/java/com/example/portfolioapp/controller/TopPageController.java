package com.example.portfolioapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.portfolioapp.authentication.CustomUserDetails;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.entity.SkillInfo;
import com.example.portfolioapp.service.SkillInfoService;

@Controller
public class TopPageController {

	@Autowired
	SkillInfoService skillInfoService;
	
	
	
    //トップ画面の表示
    @GetMapping(value = "/user/top")
    public String displayTop(Authentication loginUser,Model model) {
    
        
    	model.addAttribute("userAddRequest", new UserAddRequest());
        //emailをモデルに追加。${email}でメアドを表示出来る。
        model.addAttribute("email", loginUser.getName());
        
        // CustomUserDetailsオブジェクトを取得
        CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();
        //ユーザー名をモデルに追加
        model.addAttribute("userAddRequest", new UserAddRequest());
        model.addAttribute("hoge", userDetails.getName());
        model.addAttribute("selfIntroduction", userDetails.getSelf_introduction());//自己紹介文をモデルに追加
        
        List<SkillInfo> dataList= skillInfoService.findAll();
        model.addAttribute("datalist",dataList);      
                
    	//カテゴリ毎の合計(data)を設定
        //DBのレコードをListクラスで取得
        List<SkillInfo> expenseByStudytime = skillInfoService.SumStudyTime();
        
        
        
        
        model.addAttribute("expenseByStudytime",expenseByStudytime);
        System.out.println(expenseByStudytime);
        
        
    	
    	//配列に変換
        

        // Modelに格納
        return "user/top";
    }
    

}
