package com.jony.Dao;

import com.jony.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName,String password){
        String sqlstr="SELECT count(1) FROM t_user "+
                "WHERE user_name = ? and password = ?";
        return jdbcTemplate.queryForObject(sqlstr,new Object[]{userName,password}
        ,Integer.class);
    }

    private final static String MATCH_USER_BY_NAME = "SELECT * FROM t_user WHERE " +
            "user_name=?";
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?," +
            "last_ip=?,credits=? where user_id=?";
    public User findUserByName(final String userName){
        final User user = new User();
        jdbcTemplate.query(MATCH_USER_BY_NAME, new Object[]{userName}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setCredits(rs.getInt("credits"));
            }
        });
        return user;
    }
    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),
        user.getLastIp(),user.getCredits(),user.getUserId()
        });
    }
}
