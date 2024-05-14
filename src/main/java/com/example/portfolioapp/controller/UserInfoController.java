package com.example.portfolioapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.portfolioapp.dto.UserAddRequest;
import com.example.portfolioapp.service.UserInfoService;

@Controller
public class UserInfoController {
	
	@Autowired
    private UserInfoService userInfoService;
	
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
    public String displayTop(Model model) {
        model.addAttribute("userAddRequest", new UserAddRequest());
        return "user/top";
    }
    
	
    /**
     * ユーザー新規登録
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @RequestMapping(value = "/user/signin", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute UserAddRequest userRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "user/signin";
        }
        // ユーザー情報が登録できた場合
        userInfoService.save(userRequest);
        return "redirect:/user/top"; //トップ画面へ遷移するように変更
    }
    
	//ログイン画面の表示
    @RequestMapping("/user/login")
    public String displayLogin(Model model) {
    	return "user/login";
    }

}


