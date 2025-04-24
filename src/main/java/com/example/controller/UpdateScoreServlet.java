package com.example.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.dao.ScoreDao;

@WebServlet("/updateScore")
public class UpdateScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int scoreId = Integer.parseInt(request.getParameter("scoreId"));
        int score = Integer.parseInt(request.getParameter("score"));

        ScoreDao dao = new ScoreDao();
        dao.updateScore(scoreId, score);
        response.sendRedirect("listScore");
    }
}