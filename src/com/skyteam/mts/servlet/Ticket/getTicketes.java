package com.skyteam.mts.servlet.Ticket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.skyteam.mts.dao.impl.TicketDaoImpl;
import com.skyteam.mts.entities.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by rick- on 2017/6/29.
 */
@WebServlet(name = "getTicketes", value = "/getTicketes")
public class getTicketes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ticket> ticketList = null;

        if(request.getParameter("Status") != null){
            ticketList = new TicketDaoImpl().getTicketes(Integer.parseInt(request.getParameter("Status")));
        }

//        Cookie cookie = null;
//        if (request.getCookies() == null) {
//            HttpSession session = request.getSession();
//            cookie = new Cookie("SessionID", session.getId());
//        }
//        System.out.println(cookie);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("TEXT/JSON");
        //response.addCookie(cookie);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(new Gson().toJson(null==ticketList?new JsonObject():ticketList));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
