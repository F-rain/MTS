package com.skyteam.mts.dao.impl;

import com.skyteam.mts.dao.TicketDao;
import com.skyteam.mts.entities.Ticket;
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
public class TicketDaoImpl implements TicketDao {
    /**
     * 获取指定状态的票据列表
     *
     * @param Status
     * @return
     */
    @Override
    public List<Ticket> getTicketes(int Status) {
        List<Ticket> ticketList = null;
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TicketID FROM Ticket WHERE TicketStatus = '"+ Status +"'");

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

    /**
     * 获取指定电影名字的票据列表
     *
     * @param TicketName
     * @param Status
     * @return
     */
    @Override
    public List<Ticket> getTicketes(String TicketName, int Status) {
        List<Ticket> ticketList = null;
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT TicketID FROM Ticket WHERE TicketName = '"+ TicketName +"' AND TicketStatus = '"+ Status +"'");

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

    /**
     * 获取指定票据的详细信息
     *
     * @param TicketID
     * @return
     */
    @Override
    public Ticket getTicket(String TicketID) {
        Ticket ticket = null;

        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Ticket WHERE TicketID = '"+ TicketID +"'");

            resultSet.next();
            ticket = new Ticket();
            ticket.setTicketID(resultSet.getString("TicketID"));
            ticket.setTicketName(resultSet.getString("TicketName"));
            ticket.setLocation(resultSet.getString("Location"));
            ticket.setTicketTime(resultSet.getString("TicketTime"));
            ticket.setDirector(resultSet.getString("Director"));
            ticket.setActor(resultSet.getString("Actor"));
            ticket.setPrice(resultSet.getDouble("Price"));
            ticket.setRestNum(resultSet.getInt("RestNum"));
            ticket.setTicketStatus(resultSet.getInt("TicketStatus"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return ticket;
    }

    /**
     * 添加新的票据选项
     * TicketStatus值0为暂未开售，1为已经开售
     * @param ticket
     * @return
     */
    @Override
    public boolean setTicket(Ticket ticket) {
        boolean is_success = false;

        Connection conn = DBUtil.getConn();
        Statement statement = null;

        ticket.setTicketID(UUID.randomUUID().toString());
        try {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO Ticket(TicketID, TicketName, Location, TicketTime, Diretor, Actor, Price, RestNum, TicketStatus) VALUES ('"+ ticket.getTicketID() +"', '"+ ticket.getTicketName() +"', '"+ ticket.getLocation() +"', '"+ ticket.getTicketTime() +"', '"+ ticket.getDirector() +"', '"+ ticket.getActor() +"', '"+ ticket.getPrice() +"', '"+ ticket.getRestNum()+"', '"+ ticket.getTicketStatus() +"')");
            is_success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return is_success;
    }

    /**
     * 开放售票
     *
     * @param TicketID
     * @return
     */
    @Override
    public boolean openTicket(String TicketID) {
        boolean is_success = false;

        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE Ticket SET TIcketStatus = '1' WHERE TicketID = '"+ TicketID +"'");

            is_success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return is_success;
    }

    /**
     * 对指定的票的剩余票数进行补充
     *
     * @param TicketID
     * @param num
     * @return
     */
    @Override
    public boolean addTicket(String TicketID, int num) {
        boolean is_success = false;

        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT RestNum FROM Ticket WHERE TicketID = '"+ TicketID +"'");
            resultSet.next();
            num = num + resultSet.getInt("RestNum");

            statement.executeUpdate("UPDATE Ticket SET RestNum = '"+ num +"' WHERE TicketID = '"+ TicketID +"'");
            is_success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return is_success;
    }

    /**
     * 下订单
     *
     * @param UserID
     * @param TicketID
     * @return
     */
    @Override
    public boolean addOrder(String UserID, String TicketID, String Sites, int Num) {
        boolean is_success = false;

        Connection conn = DBUtil.getConn();
        Statement statement = null;
        String OrderID = UUID.randomUUID().toString();
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT RestNum FROM Ticket WHERE TicketID = '"+ TicketID +"'");
            resultSet.next();
            int num = 0;
            if (resultSet.getInt("RestNum") >= Num) {
                num = resultSet.getInt("RestNum") - Num;
            }
            else return false;
            statement.executeUpdate("UPDATE Ticket SET RestNum = '"+ num +"' WHERE TicketID = '"+ TicketID +"'");

            statement.executeUpdate("INSERT INTO `Order`(OrderID, UserID, TicketID, Sites, Num) VALUES ('"+ OrderID +"','"+ UserID +"', '"+ TicketID +"', '"+ Sites +"', '"+ Num +"')");
            is_success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return is_success;
    }

    /**
     * 退票
     *
     * @param UserID
     * @param TicketID
     * @return
     */
    @Override
    public boolean refundsTicket(String UserID, String TicketID) {
        boolean is_success = false;
        Connection conn = DBUtil.getConn();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT RestNum FROM Ticket WHERE TicketID = '"+ TicketID +"'");
            resultSet.next();
            int num = resultSet.getInt("RestNum") + 1;
            statement.executeUpdate("UPDATE Ticket SET RestNum = '"+ num +"' WHERE TicketID = '"+ TicketID +"'");

            statement.executeUpdate("DELETE FROM `Order` WHERE UserID = '"+ UserID +"' AND TicketID = '"+ TicketID +"'");
            is_success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConn(conn, statement);
        }

        return is_success;
    }
}
