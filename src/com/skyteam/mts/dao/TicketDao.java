package com.skyteam.mts.dao;

import com.skyteam.mts.entities.Ticket;

import java.util.List;

/**
 * Created by rick- on 2017/6/29.
 */
public interface TicketDao {

    /**
     * 获取指定状态的票据列表
     * @param Status
     * @return
     */
    public List<Ticket> getTicketes(int Status);

    /**
     * 获取指定电影名字的票据列表
     * @param TicketName
     * @param Status
     * @return
     */
    public List<Ticket> getTicketes(String TicketName, int Status);

    /**
     * 获取指定票据的详细信息
     * @param TicketID
     * @return
     */
    public Ticket getTicket(String TicketID);

    /**
     * 添加新的票据选项
     * @param ticket
     * @return
     */
    public boolean setTicket(Ticket ticket);

    /**
     * 开放售票
     * @param TicketID
     * @return
     */
    public boolean openTicket(String TicketID);

    /**
     * 对指定的票的剩余票数进行补充
     * @param TicketID
     * @param num
     * @return
     */
    public boolean addTicket(String TicketID, int num);

    /**
     * 下订单
     * @param UserID
     * @param TicketID
     * @return
     */
    public boolean addOrder(String UserID, String TicketID, String Sites, int Num);


    /**
     * 退票
     * @param UserID
     * @param TicketID
     * @return
     */
    public boolean refundsTicket(String UserID, String TicketID);
}
