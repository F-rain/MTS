package com.skyteam.mts.servlet.Ticket;

import com.skyteam.mts.dao.impl.TicketDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rick- on 2017/6/29.
 */
@WebServlet(name = "refundsTicket", value = "/refundsTicket")
public class refundsTicket extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean is_success = false;
        if (null != request.getParameter("UserID") && null != request.getParameter("TicketID")){
            is_success = new TicketDaoImpl().refundsTicket(request.getParameter("UserID"), request.getParameter("TicketID"));
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("TEXT/JSON");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(Boolean.toString(is_success));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
