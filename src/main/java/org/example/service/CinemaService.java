package org.example.service;


import org.example.dto.request.CinemaRequestDTO;
import org.example.dto.response.CinemaResponseDTO;
import org.example.dto.update.CinemaUpdateDTO;

public interface CinemaService {
    CinemaResponseDTO find(Long cinemaId);

    void save(CinemaRequestDTO dto);

    void update(CinemaUpdateDTO dto);

    void delete(Long cinemaId);
}
