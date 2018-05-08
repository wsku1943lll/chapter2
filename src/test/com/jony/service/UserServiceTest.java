package com.jony.service;

import com.jony.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:jony-context.xml"})
@Transactional(transactionManager = "transcationManager")
@Rollback(value = false)
public class UserServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Test
    public void hasMatchUser(){
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin","123");
        System.out.println("["+b1+"]["+b2+"]" );
        if(b1==true){
            return ;
        }
    }

    @Test
    public void findUserByName(){
        User user = userService.findUserByUserName("admin");
        System.out.println(user.getUserName().equals("admin"));
    }
}
