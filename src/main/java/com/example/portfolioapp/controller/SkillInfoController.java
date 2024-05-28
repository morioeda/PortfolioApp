package com.example.portfolioapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.portfolioapp.authentication.CustomUserDetails;
import com.example.portfolioapp.dto.SkillAddRequest;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.dto.UserUpdateRequest;
import com.example.portfolioapp.entity.SkillInfo;
import com.example.portfolioapp.service.SkillInfoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SkillInfoController {
	
	@Autowired
	SkillInfoService skillInfoService;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
    //項目追加ページの表示
    @GetMapping(value = "/user/skilladd")
    public String displaySkillAdd(@RequestParam Long category_id, Authentication loginUser,Model model) {
    	
    	//パラメーター（カテゴリーIDを取得）
    	model.addAttribute("category_id", category_id);
    	
    	//フォームオブジェクトをモデルに追加
    	model.addAttribute("skillAddRequest", new SkillAddRequest());
        
        // CustomUserDetailsオブジェクトを取得
        CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();
        
        //ユーザー名をモデルに追加 ※ユーザー名をフッターに表示させるためのコード
        model.addAttribute("userAddRequest", new UserAddRequest());//UserAddになっていたので修正
        model.addAttribute("hoge", userDetails.getName());
        model.addAttribute("user_id",userDetails.getId());//Idを取得し、Viewに渡す
        
        
        return "user/skilladd";
    }
    
    
    //項目追加ページ
    @RequestMapping(value = "/user/skilladd", method = RequestMethod.POST)
    public String add(@RequestParam Long category_id,@Validated @ModelAttribute SkillAddRequest skillRequest, BindingResult result, Model model,Authentication loginUser) {
    	  
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            
            
          // ユーザー名の重複をチェック
         if (skillInfoService.isItemExist(skillRequest.getName())) {
                result.rejectValue("name", "duplicate", "入力した項目名は既に使用されています");
            }
            
            model.addAttribute("validationError", errorList);
            model.addAttribute("userAddRequest", new UserAddRequest());
                     
            CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();
            model.addAttribute("hoge", userDetails.getName());
            model.addAttribute("user_id",userDetails.getId());//Idを取得し、Viewに渡す
            
            //バリデーションチェック後もパラメーターを保持
            model.addAttribute("category_id", category_id);
            
            System.out.println(model.getAttribute("validationError"));
            
            return "user/skilladd";
        }
        
        // 項目名と学習時間情報をDBへ登録
        skillInfoService.add(skillRequest);
        
        //追記↓
//        CustomUserDetails updatedUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
        
		/*  //セキュリティコンテキストを更新
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
		        updatedUserDetails, authentication.getCredentials(), updatedUserDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authToken);
		//ここまで
		*/        
        return "redirect:/user/skilledit"; //項目一覧画面へ遷移するように変更
    }
	

}
