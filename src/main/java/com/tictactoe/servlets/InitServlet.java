package com.tictactoe.servlets;

import com.tictactoe.Field;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Field field=new Field();
        session.setAttribute("field", field);
        session.setAttribute("data",field.getFieldData());
        RequestDispatcher requestDispatcher = session.getServletContext().getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
