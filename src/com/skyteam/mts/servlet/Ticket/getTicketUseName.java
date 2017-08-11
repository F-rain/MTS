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
import java.util.List;

/**
 * Created by rick- on 2017/6/29.
 */
@WebServlet(name = "getTicketUseName", value = "/getTicketUseName")
public class getTicketUseName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ticket> ticketList = null;

        if (null != request.getParameter("TicketName") && null != request.getParameter("Status")){
            ticketList = new TicketDaoImpl().getTicketes(request.getParameter("TicketName"), Integer.parseInt(request.getParameter("Status")));
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
