package com.tictactoe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //creating a new session
        HttpSession currentSession = req.getSession(true);

        //creating the game field
        Field field = new Field();
        Map<Integer, Sign> fieldDate = field.getField();

        //getting values list of the field
        List<Sign> data = field.getFieldData();

        // Додавання до сесії параметрів поля (буде потрібно для зберігання стану між запитами)
        currentSession.setAttribute("field", field);

        //and values of the field, sorted by index (it's needed for the painting crosses and zeroes
        currentSession.setAttribute("data", data);

        //redirect request on to the index.jsp from server
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
