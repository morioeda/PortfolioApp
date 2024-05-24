package com.example.portfolioapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.portfolioapp.authentication.CustomUserDetails;
import com.example.portfolioapp.dto.SkillAddRequest;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.dto.UserUpdateRequest;
import com.example.portfolioapp.service.SkillInfoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SkillInfoController {
	
	@Autowired
	SkillInfoService skillInfoService;
	
	
	
	  //自己紹介編集画面の表示
    @GetMapping(value = "/user/skilladd")
    public String displaySkillAdd(Authentication loginUser,Model model) {
    	
    	//フォームオブジェクトをモデルに追加
    	model.addAttribute("skillAddRequest", new SkillAddRequest());
        
        // CustomUserDetailsオブジェクトを取得
        CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();
        
        //ユーザー名をモデルに追加 ※ユーザー名をフッターに表示させるためのコード
        model.addAttribute("skillAddRequest", new SkillAddRequest());//UserAddになっていたので修正
        model.addAttribute("hoge", userDetails.getName());
        model.addAttribute("id",userDetails.getId());//Idを取得し、Viewに渡す        
        return "user/skilladd";
    }
    
    @RequestMapping(value = "/user/skilladd", method = RequestMethod.POST)
    public String add(@Validated @ModelAttribute SkillAddRequest skillRequest, BindingResult result, Model model,HttpServletRequest request) {
    	  model.addAttribute("skillAddRequest", new SkillAddRequest());
    	  
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            
            model.addAttribute("validationError", errorList);
            
            System.out.println(model.getAttribute("validationError"));
            
            return "user/skilladd";
        }
        
        // 項目名と学習時間情報をDBへ登録
        skillInfoService.add(skillRequest);
        
        return "redirect:/user/skilledit"; //項目一覧画面へ遷移するように変更
    }
	

}
