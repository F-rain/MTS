package com.skyteam.mts.servlet.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.skyteam.mts.dao.impl.TicketDaoImpl;
import com.skyteam.mts.dao.impl.UserDaoImpl;
import com.skyteam.mts.entities.Ticket;
import com.skyteam.mts.listener.MySessionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by rick- on 2017/6/29.
 */
@WebServlet(name = "getMyTicket", value = "/getmyticket")
public class getMyTicket extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ticket> ticketList = null;
        String UserID = request.getParameter("UserID");

        if (null != UserID){
            ticketList = new UserDaoImpl().getMyTicketes(UserID);
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("TEXT/JSON");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(new Gson().toJson(null==ticketList?new JsonObject():ticketList));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
