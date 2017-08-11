package com.skyteam.mts.dao.impl;

import com.skyteam.mts.dao.UserDao;
import com.skyteam.mts.entities.Ticket;
import com.skyteam.mts.entities.User;
import com.skyteam.mts.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by rick- on 2017/6/29.
 */
public class UserDaoImpl implements UserDao {

    /**
     * 通过sql语句获取用户对象
     * @param sql
     * @return
     */
    private User getUserUseSql(String sql){
        User user =null;
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                user = new User();
                user.setUserID(resultSet.getString("UserID"));
                user.setUserTel(resultSet.getString("UserTel"));
                user.setUserName(resultSet.getString("UserName"));
                user.setPassword(resultSet.getString("Password"));
                user.setUserLevel(resultSet.getInt("UserLevel"));
                user.setRestMoney(resultSet.getDouble("RestMoney"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return user;
    }



    /**
     * 通过手机号获取用户信息
     *
     * @param UserTel （用户手机号）
     * @return User（一个用户的对象）
     */
    @Override
    public User getUser(String UserTel) {
        String sql = "SELECT * FROM `User` WHERE UserTel = '"+ UserTel +"'";

        return getUserUseSql(sql);
    }

    /**
     * 通过用户的手机号和密码获取用户信息
     *
     * @param UserTel  （用户手机号）
     * @param Password （密码）
     * @return User（一个用户的对象）
     */
    @Override
    public User getUser(String UserTel, String Password) {
        String sql = "SELECT * FROM `User` WHERE UserTel = '"+ UserTel +"' AND Password = '"+ Password+"'";

        return getUserUseSql(sql);
    }

    /**
     * 通过用户手机号和用户手机唯一识别码创建用户
     * status值 0是普通用户 1是管理员用户
     * @param UserTel
     * @param Password
     * @return
     */
    @Override
    public User setUser(String UserTel, String Password) {
        boolean success = false;

        if (getUser(UserTel) != null){
            return null;
        }

        //生成用户唯一标识ID
        String UserID = UUID.randomUUID().toString();
        Connection conn = DBUtil.getConn();
        Statement statement = null;

        try {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO `User` VALUES ('"+ UserID +"', '新手上路', '"+ Password +"',  '"+ UserTel +"', '"+ 0 +"', '"+ 0 +"')");
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        if (success){
            return getUser(UserTel);
        }

        return null;
    }

    /**
     * 获取用户已经购买的票
     *
     * @param UserID
     * @return
     */
    @Override
    public List<Ticket> getMyTicketes(String UserID) {
        List<Ticket> ticketList = null;

        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TicketID FROM `Order` WHERE UserID = '"+ UserID +"'");
            ticketList = new ArrayList<>();
            while (resultSet.next()){
                Ticket ticket = new TicketDaoImpl().getTicket(resultSet.getString("TicketID"));
                ticketList.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return ticketList;
    }

    @Override
    public boolean updateUser(String UserID, User user) {
        Connection conn = DBUtil.getConn();
        Statement statement = null;

        try {
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE `User` SET UserName = '"+ user.getUserName() +"' WHERE UserID = '"+ UserID +"'");
            statement.executeUpdate("UPDATE `User` SET UserTel = '"+ user.getUserTel() +"' WHERE UserID = '"+ UserID +"'");
            statement.executeUpdate("UPDATE `User` SET Password = '"+ user.getPassword() +"' WHERE UserID = '"+ UserID +"'");
            statement.executeUpdate("UPDATE `User` SET UserLevel = '"+ user.getUserLevel() +"' WHERE UserID = '"+ UserID +"'");
            statement.executeUpdate("UPDATE `User` SET RestMoney = '"+ user.getRestMoney() +"' WHERE UserID = '"+ UserID +"'");

            statement.close();
            conn.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return false;
    }
}
