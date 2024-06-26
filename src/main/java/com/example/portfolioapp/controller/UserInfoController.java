package com.example.portfolioapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.portfolioapp.dto.StudyTimeUpdateRequest;
import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.dto.UserUpdateRequest;
import com.example.portfolioapp.entity.CategoriesInfo;
import com.example.portfolioapp.entity.SkillInfo;
import com.example.portfolioapp.service.SkillInfoService;
import com.example.portfolioapp.service.UserInfoService;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class UserInfoController {
	
    @Autowired
    private UserInfoService userInfoService;
	
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    
    @Autowired
	SkillInfoService skillInfoService;
    
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
    
    
    /**
     * ユーザー新規登録
     * @param userRequest リクエストデータ
     * @param model 
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/signin", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute UserAddRequest userRequest, BindingResult result, Model model,HttpServletRequest request,String email,String password) {
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            
            model.addAttribute("validationError", errorList);
            System.out.println(model.getAttribute("validationError"));
                        
            return "user/signin";
        }
        
        // ユーザー情報をDBへ登録
        userInfoService.save(userRequest);
        
        //新規登録後自動的にログインさせる
        try {
            // 保存されたユーザー情報を使ってUserDetailsを取得
            UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getEmail());
            
            // 認証トークンを作成（ユーザー名、パスワード、およびユーザーの権限情報を保持）
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, userRequest.getPassword(), userDetails.getAuthorities());

            // セキュリティコンテキストに認証情報を設定
            SecurityContextHolder.getContext().setAuthentication(authToken);
            
            // 認証情報をセッションに保存
            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            
        } catch (Exception e) {
//            e.printStackTrace();
            return "user/signin"; 
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        model.addAttribute("userName", userName);
        
        String selfIntroduction = userDetails.getSelf_introduction();
        model.addAttribute("self_introduction",selfIntroduction);
        
        
        //ユーザー名をモデルに追加 ※ユーザー名をフッターに表示させるためのコード
        model.addAttribute("userUpdateRequest", new UserUpdateRequest());//Addになっていたので修正
        model.addAttribute("hoge", userDetails.getName());
        model.addAttribute("id",userDetails.getId());//Idを取得し、Viewに渡す        
        return "user/textedit";
    }
    
    //自己紹介の編集
    @RequestMapping(value="/user/textedit", method=RequestMethod.POST)
    public String edit(@Validated @ModelAttribute UserUpdateRequest userRequest,BindingResult result, Model model,Authentication authentication) {
    	//入力チェック
    	 if (result.hasErrors()) {
             // 入力チェックエラーの場合
             List<String> errorList = new ArrayList<String>();
             for (ObjectError error : result.getAllErrors()) {
                 errorList.add(error.getDefaultMessage());
             }
             
             model.addAttribute("validationError", errorList);
             
             CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
             model.addAttribute("id",userDetails.getId());

             
             return "user/textedit";
         }	 
    	 
    	 
         // ユーザー情報をDBへ登録
         userInfoService.update(userRequest);
         
         //自己紹介文追加後のDB情報を表示させる
         
			/* authentication.getName()は、現在ログインしているユーザーのメールアドレスを返す
			 userDetailsService.loadUserByUsernameメソッドを使って、メールアドレスに基づいてデータベースからユーザー情報を取得*/
         CustomUserDetails updatedUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
         
         //セキュリティコンテキストを更新
         UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                 updatedUserDetails, authentication.getCredentials(), updatedUserDetails.getAuthorities());
         SecurityContextHolder.getContext().setAuthentication(authToken);
         
         
         return "redirect:/user/top";
    }
    
    //スキル編集ページの表示
    @GetMapping(value = "/user/skilledit")
    public String displaySkillEdit(SkillInfo skillInfo,Authentication loginUser,Model model,Authentication authentication) {
    	   // CustomUserDetailsオブジェクトを取得
        CustomUserDetails userDetails = (CustomUserDetails) loginUser.getPrincipal();
        //ユーザー名をモデルに追加
        model.addAttribute("userAddRequest", new UserAddRequest());
        model.addAttribute("hoge", userDetails.getName());
        
        Long userId = userDetails.getId();
        
        //learning_dataの情報を取得して表示させる
        List<SkillInfo> dataList= skillInfoService.findAll(userId);//skillInfo.getUser_id()
        model.addAttribute("datalist",dataList);
//        model.addAttribute("userSearchRequest", new SkillAddRequest());※要らないぽい
        
        return "user/skilledit";
    }
    
    //学習時間の更新と学習データの削除
	 @RequestMapping(value="/user/skilledit", method=RequestMethod.POST)
	public String updateTime(@ModelAttribute StudyTimeUpdateRequest studyTimeUpdateRequest,@RequestParam String action,Model model,Authentication authentication) {
		
		 if (action.equals("update")) {
		       skillInfoService.update(studyTimeUpdateRequest);
		   } else if (action.equals("delete")) {
		       skillInfoService.deleteData(studyTimeUpdateRequest.getId());
		   }
		 
		return "redirect:/user/skilledit";
	}
    
}