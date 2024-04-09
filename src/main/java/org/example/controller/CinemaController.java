package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dds.DataDeserializer;
import org.example.ds.DataSerializer;
import org.example.dto.request.CinemaRequestDTO;
import org.example.dto.response.CinemaResponseDTO;
import org.example.dto.update.CinemaUpdateDTO;
import org.example.service.CinemaService;
import org.example.service.implement.CinemaServiceImpl;


import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/cinema")
public class CinemaController extends HttpServlet {

    private final CinemaService cinemaService = new CinemaServiceImpl();
    private final DataSerializer<CinemaResponseDTO> serializer = new DataSerializer<>();
    private final DataDeserializer<CinemaRequestDTO> deserializer = new DataDeserializer<>();
    private final DataDeserializer<CinemaUpdateDTO> deserializerForUpdateDTO = new DataDeserializer<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        CinemaResponseDTO dto = cinemaService.find(id);
        String json = serializer.toJson(dto);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        CinemaRequestDTO dto = deserializer.fromJson(json, CinemaRequestDTO.class);
        cinemaService.save(dto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        CinemaUpdateDTO dto = deserializerForUpdateDTO.fromJson(json, CinemaUpdateDTO.class);
        cinemaService.update(dto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        cinemaService.delete(id);
    }
}
