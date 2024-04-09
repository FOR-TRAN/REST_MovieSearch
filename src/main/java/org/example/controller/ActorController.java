package org.example.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.dds.DataDeserializer;
import org.example.ds.DataSerializer;
import org.example.dto.request.ActorRequestDTO;
import org.example.dto.response.ActorResponseDTO;
import org.example.dto.update.ActorUpdateDTO;
import org.example.service.ActorService;
import org.example.service.implement.ActorServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/actor")
public class ActorController extends HttpServlet {

    /*создается приватное поле movieService, которое инициализируется экземпляром класса MovieServiceImpl.
     Это указывает на то, что ActorController будет использовать MovieServiceImpl для выполнения операций
      связанных с фильмами
     */
    private final ActorService actorService = new ActorServiceImpl();

    /*Создается приватное поле serializer, которое является экземпляром класса DataSerializer для объектов
     типа MovieResponseDTO. Этот объект будет использоваться для сериализации данных
      типа MovieResponseDTO
     */
    private final DataSerializer<ActorResponseDTO> serializer = new DataSerializer<>();

    //будет использоваться для десериализации данных типа MovieRequestDTO
    private final DataDeserializer<ActorRequestDTO> deserializer = new DataDeserializer<>();

    //десериализовывает данные типа MovieUpdateDTO в объекты
    private final DataDeserializer<ActorUpdateDTO> deserializerForUpdateDTO = new DataDeserializer<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        ActorResponseDTO dto = actorService.find(id);
        String json = serializer.toJson(dto);
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        ActorRequestDTO dto = deserializer.fromJson(json, ActorRequestDTO.class);
        actorService.save(dto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getReader().lines().collect(Collectors.joining());
        ActorUpdateDTO dto = deserializerForUpdateDTO.fromJson(json, ActorUpdateDTO.class);
        actorService.update(dto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        actorService.delete(id);
    }
}
