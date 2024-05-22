package com.example.portfolioapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.portfolioapp.authentication.CustomUserDetails;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.dto.UserUpdateRequest;
import com.example.portfolioapp.service.UserInfoService;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class UserInfoController {
	
    @Autowired
    private UserInfoService userInfoService;
	
    @Autowired
    private UserDetailsService userDetailsService;
    
    /**
     * ユーザー新規登録画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @GetMapping(value = "/user/signin")
    public String displayAdd(Model model) {
        model.addAttribute("userAddRequest", new UserAddRequest());
        return "user/signin";
    }
    
    
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
        
        return "user/top";
    }
    
    /**
     * ユーザー新規登録
     * @param userRequest リクエストデータ
     * @param model 
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/signin", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute UserAddRequest userRequest, BindingResult result, Model model,HttpServletRequest request) {
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            
            model.addAttribute("validationError", errorList);
            return "user/signin";
        }
        
        // ユーザー情報をDBへ登録
        userInfoService.save(userRequest);
        
        //新規登録後自動的にログインさせる
        try {
            // 保存されたユーザー情報を使ってUserDetailsを取得
            UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getEmail());
            
            // 認証トークンを作成
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, userRequest.getPassword(), userDetails.getAuthorities());

            // セキュリティコンテキストに認証情報を設定
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            // HttpServletRequestを使ってユーザーをログインさせる

        } catch (Exception e) {
            e.printStackTrace();
            return "user/top"; // ログインページにリダイレクト
        }
        
        
        return "redirect:/user/top"; //トップ画面へ遷移するように変更
    }
    
	//ログイン画面の表示
    @RequestMapping("/user/login")
    public String displayLogin(Model model) {
    	return "user/login";
    }
    
    
    
    //自己紹介編集画面の表示
    @GetMapping(value = "/user/textedit")
    public String displayEdit(Authentication loginUser,Model model) {
        
        // CustomUserDetailsオブジェクトを取得
        CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();
        
       
        
        //ユーザー名をモデルに追加 ※ユーザー名をフッターに表示させるためのコード
        model.addAttribute("userUpdateRequest", new UserUpdateRequest());//Addになっていたので修正
        model.addAttribute("hoge", userDetails.getName());
        model.addAttribute("id",userDetails.getId());//Idを取得し、Viewに渡す        
        return "user/textedit";
    }
    
    //自己紹介の編集
    @RequestMapping(value="/user/textedit", method=RequestMethod.POST)
    public String edit(@Validated @ModelAttribute UserUpdateRequest userRequest,BindingResult result, Model model) {
    	//入力チェック
    	 if (result.hasErrors()) {
             // 入力チェックエラーの場合
             List<String> errorList = new ArrayList<String>();
             for (ObjectError error : result.getAllErrors()) {
                 errorList.add(error.getDefaultMessage());
             }
             
             model.addAttribute("validationError", errorList);
             return "user/textedit";
         }	 
    	 
    	 //現在ログインしているユーザー情報の取得※不要
			/* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			*/
    	 
         // ユーザー情報をDBへ登録
         userInfoService.update(userRequest); 
         
         return "redirect:/user/top";
    }
    
    

}


