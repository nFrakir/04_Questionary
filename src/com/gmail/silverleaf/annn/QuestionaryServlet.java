package com.gmail.silverleaf.annn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class QuestionaryServlet extends HttpServlet {
    private final static String TEMPLATE = "<html>" +
            "<head><title>Questionary statistic</title>" +
            "<meta charset=\"utf-8\">\n" +
            "<link rel='stylesheet' href='style.css' type='text/css' media='all' />" +
            "<body>" +
            "<b>Thank you for your answers, %s.</b>%s" +
            "<br><a href = \"index.html\">Return to main page</a>" +
            "</body></html>";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs = req.getSession();
        Statistic st = (Statistic) hs.getAttribute("stat");
        if (st == null) {
            st = Statistic.getInstance();
            hs.setAttribute("stat", st);
        }
        String voite = req.getParameter("likeJava");
        int index = (voite.equals("yes")) ? 0 : 1;
        st.addAnswer("likeJava", index);

        voite = req.getParameter("skilled");
        index = (voite.equals("yes")) ? 0 : 1;
        st.addAnswer("skilled", index);

        voite = req.getParameter("knowHtml");
        index = (voite.equals("yes")) ? 0 : 1;
        st.addAnswer("knowHtml", index);

        PrintWriter pw = resp.getWriter();
        String name = req.getParameter("userName") + " " + req.getParameter("userSirname");

        pw.println(String.format(TEMPLATE, name, st.toString()));
        pw.close();
    }
}
