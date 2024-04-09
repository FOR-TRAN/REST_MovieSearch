package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.ActorService;
import org.example.service.implement.ActorServiceImpl;


import java.io.IOException;

@WebServlet("/actor_movie")
public class FindMovieController extends HttpServlet {

    private final ActorService actorService = new ActorServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long actor_id = Long.parseLong(req.getParameter("actor_id"));
        long movie_id = Long.parseLong(req.getParameter("movie_id"));
        actorService.findMovie(actor_id, movie_id);
    }
}
