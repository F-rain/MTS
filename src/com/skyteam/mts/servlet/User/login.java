package com.skyteam.mts.servlet.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.skyteam.mts.dao.impl.UserDaoImpl;
import com.skyteam.mts.entities.User;
import com.skyteam.mts.listener.MySessionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by rick- on 2017/6/29.
 */
@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        if (request.getParameter("UserTel") != null && request.getParameter("password") != null){
            user = new UserDaoImpl().getUser(request.getParameter("UserTel"), request.getParameter("password"));
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("TEXT/JSON");
        response.setHeader("Access-Control-Allow-Origin", "*");
//        if (request.getCookies()[0] != null){
//            Cookie cookie = request.getCookies()[0];
//            HttpSession session = MySessionContext.getSession(cookie.getValue());
//            session.setAttribute("UserID", user.getUserID());
//            session.setAttribute("UserName", user.getUserName());
//            session.setAttribute("password", user.getPassword());
//            session.setAttribute("UserTel", user.getUserTel());
//            session.setAttribute("UserLevel", user.getUserLevel());
//            session.setAttribute("RestMoney", user.getRestMoney());
//        }
        response.getWriter().write(new Gson().toJson(user == null?new JsonObject():user));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
