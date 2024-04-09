package org.example.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.dds.DataDeserializer;
import org.example.ds.DataSerializer;
import org.example.dto.request.MovieRequestDTO;
import org.example.dto.response.MovieResponseDTO;
import org.example.dto.update.MovieUpdateDTO;
import org.example.service.MovieService;
import org.example.service.implement.MovieServiceImpl;


import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/movie")
public class MovieController extends HttpServlet {
    private final MovieService movieService = new MovieServiceImpl();
    private final DataSerializer<MovieResponseDTO> serializer = new DataSerializer<>();
    private final DataDeserializer<MovieRequestDTO> deserializer = new DataDeserializer<>();
    private final DataDeserializer<MovieUpdateDTO> deserializerForUpdateDTO = new DataDeserializer<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        MovieResponseDTO dto = movieService.find(id);
        String json = serializer.toJson(dto);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        MovieRequestDTO dto = deserializer.fromJson(json, MovieRequestDTO.class);
        movieService.save(dto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        MovieUpdateDTO dto = deserializerForUpdateDTO.fromJson(json, MovieUpdateDTO.class);
        movieService.update(dto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        movieService.delete(id);
    }
}
