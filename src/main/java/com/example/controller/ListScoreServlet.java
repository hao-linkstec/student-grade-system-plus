package com.example.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.dao.ScoreDao;
import com.example.dto.ScoreDto;

@WebServlet("/listScore")
public class ListScoreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ScoreDao dao = new ScoreDao();
        List<ScoreDto> list = dao.getAllScores();
        request.setAttribute("scoreList", list);
        request.setAttribute("searchedClass", "");
        request.setAttribute("searchedName", "");
        request.getRequestDispatcher("listScore.jsp").forward(request, response);
    }
}