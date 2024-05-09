package com.example.portfolioapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.portfolioapp.dto.UserAddRequest;

@Controller
public class UserInfoController {
	
    /**
     * ユーザー新規登録画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @GetMapping(value = "/user/add")
    public String displayAdd(Model model) {
        model.addAttribute("userAddRequest", new UserAddRequest());
        return "";
    }
	
	
}


