package com.tictactoe.servlets;

import com.tictactoe.Field;
import com.tictactoe.Sign;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@WebServlet (name = "LogicServlet", value = "/logic")
public class LogicServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int index=Integer.parseInt(req.getParameter("click"));
        HttpSession session= req.getSession();
        Field field= (Field) session.getAttribute("field");
        if (field.getField().get(index).equals(Sign.EMPTY)) {
            field.getField().put(index, Sign.CROSS);
            if (Objects.equals(checkWinner(field), Sign.CROSS)){
                session.setAttribute("winner",Sign.CROSS);
                List<Sign> data = field.getFieldData();
                session.setAttribute("data", data);
                session.setAttribute("field", field);
                resp.sendRedirect("/index.jsp");
                return;
            }
            int noughtIndex = field.getEmptyFieldIndex();
            if (noughtIndex!=-1) {
                field.getField().put(noughtIndex,Sign.NOUGHT);
                List<Sign> data = field.getFieldData();
                session.setAttribute("data", data);
                session.setAttribute("field", field);
                if (Objects.equals(checkWinner(field), Sign.NOUGHT))
                {
                    session.setAttribute("winner",Sign.NOUGHT);
                    resp.sendRedirect("/index.jsp");
                    return;
                }
                resp.sendRedirect("/index.jsp");

            }
            else {
                List<Sign> data = field.getFieldData();
                session.setAttribute("winner",Sign.EMPTY);
                session.setAttribute("data", data);
                session.setAttribute("field", field);
                resp.sendRedirect("/index.jsp");
            }
        }
        else {
            resp.sendRedirect("/index.jsp");
        }
    }


    private Sign checkWinner(Field field)
    {
        Sign winner = field.checkWin();
        if (Sign.CROSS == winner) {
            return Sign.CROSS;
        } else if (Sign.NOUGHT == winner) {
            return Sign.NOUGHT;
        }
        return null;
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
