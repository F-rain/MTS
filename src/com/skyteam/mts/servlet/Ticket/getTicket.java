package com.skyteam.mts.servlet.Ticket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.skyteam.mts.dao.impl.TicketDaoImpl;
import com.skyteam.mts.entities.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rick- on 2017/6/29.
 */
@WebServlet(name = "getTicket", value = "/getTicket")
public class getTicket extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ticket ticket = null;

        if (null != request.getParameter("TicketID")){
            ticket = new TicketDaoImpl().getTicket(request.getParameter("TicketID"));
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("TEXT/JSON");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(new Gson().toJson(null==ticket?new JsonObject():ticket));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
