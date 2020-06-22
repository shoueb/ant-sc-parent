package com.ant.admin.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ant.admin.shiro.common.Result;
import com.ant.admin.shiro.model.UserDO;
import com.ant.admin.shiro.service.RoleService;
import com.ant.admin.shiro.service.UserService;

/**
 * @Description: 测试Controller
 * @Author: Liu
 * @Date: 2020/6/15 10:06
 */
@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    /**
     * to登录页面
     * @return
     */
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    /**
     *登录成功跳转到主页面
     * @return
     */
    @GetMapping({ "/main" })
    String index() {
        return "main";
    }

    /**
     * 登录请求
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    Result Login(String username, String password) {
        try {
            //构造用户身份验证的token
            UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            //获取当前用户主体
            Subject subject= SecurityUtils.getSubject();
            subject.login(token);
            return Result.ok();
        }catch (NullPointerException | AccountException e){
            return Result.error(e.getMessage());
        }
    }


    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "redirect:/login";
    }

    /**
     * 根据用户id获取用户信息
     * @return
     */
    @GetMapping("/getById")
    @ResponseBody
    public UserDO get(Long id){
        UserDO userDO = userService.getById(id);
        return userDO;
    }

    /**
     * 获取用户角色标识
     * @param username
     * @return
     */
    @PostMapping("getSign")
    @ResponseBody
    public String getSign(String username){
        String sign=roleService.getSignByUsername(username);
        return sign;
    }




}
