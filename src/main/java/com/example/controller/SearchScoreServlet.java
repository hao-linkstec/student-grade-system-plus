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

@WebServlet("/searchScore")
public class SearchScoreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String className = request.getParameter("className");
        String stuName = request.getParameter("stuName");

        ScoreDao dao = new ScoreDao();
        List<ScoreDto> list = dao.searchScore(className, stuName);
        request.setAttribute("scoreList", list);
        request.setAttribute("searchedClass", className);
        request.setAttribute("searchedName", stuName);
        request.getRequestDispatcher("listScore.jsp").forward(request, response);
    }
}