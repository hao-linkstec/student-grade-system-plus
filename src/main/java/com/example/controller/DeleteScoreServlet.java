package com.example.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.dao.ScoreDao;

@WebServlet("/deleteScore")
public class DeleteScoreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int scoreId = Integer.parseInt(request.getParameter("scoreId"));

        ScoreDao dao = new ScoreDao();
        dao.deleteScore(scoreId);
        response.sendRedirect("listScore");
    }
}