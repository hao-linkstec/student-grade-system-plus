package com.example.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.dao.ScoreDao;

@WebServlet("/addScore")
public class AddScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stuName = request.getParameter("stuName");
        String className = request.getParameter("className");
        int score = Integer.parseInt(request.getParameter("score"));

        ScoreDao dao = new ScoreDao();
        dao.addScore(stuName, className, score);
        response.sendRedirect("listScore");
    }
}