package com.jony.service;

import com.jony.Dao.LoginLogDao;
import com.jony.Dao.UserDao;
import com.jony.domain.LoginLog;
import com.jony.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String userName,String password){
        int matchcount = userDao.getMatchCount(userName,password);
        if(matchcount>0){
            System.out.println(userName+" has visited");
        }else{
            System.out.println("No "+userName+" has matched");
        }
        return matchcount>0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByName(userName);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
