package com.skyteam.mts.dao;

import com.skyteam.mts.entities.Ticket;
import com.skyteam.mts.entities.User;

import java.util.List;

/**
 * Created by rick- on 2017/6/29.
 */
public interface UserDao {
    /**
     * 通过手机号获取用户信息
     * @param UserTel（用户手机号）
     * @return User（一个用户的对象）
     */
    public User getUser(String UserTel);

    /**
     * 通过用户的手机号和手机唯一识别码获取用户信息
     * @param UserTel（用户手机号）
     * @param Password（密码）
     * @return User（一个用户的对象）
     */
    public User getUser(String UserTel, String Password);

    /**
     * 通过用户手机号和密码创建用户
     * @param UserTel
     * @param Password
     * @return
     */
    public User setUser(String UserTel, String Password);

    /**
     * 获取用户已经购买的票
     * @param UserID
     * @return
     */
    public List<Ticket> getMyTicketes(String UserID);

    public boolean updateUser(String UserID, User user);
}
