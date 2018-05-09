package com.jony.web;

import com.jony.domain.User;
import com.jony.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController {
    private UserService userService;

    @RequestMapping(value = "/login.html")
    public String loginPage(){
        System.out.println("visit login.html");
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request,
                                   LoginCommand command){
        System.out.println("visit loginCheck.html");
        System.out.println("UserName:"+command.getUserName());
        System.out.println("password:"+command.getPassword());
        boolean isValidUser = userService.hasMatchUser(command.getUserName(),command.getPassword());
        if(!isValidUser){
            return new ModelAndView("login","error","用户名货密码错误！");
        }else{
            User user = userService.findUserByUserName(command.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user",user);
            return new ModelAndView("main");
        }
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

}
