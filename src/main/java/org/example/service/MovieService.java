package org.example.service;


import org.example.dto.request.MovieRequestDTO;
import org.example.dto.response.MovieResponseDTO;
import org.example.dto.update.MovieUpdateDTO;
import org.example.entity.Movie;

import java.util.Set;

public interface MovieService {

    MovieResponseDTO find(Long id);

    void save(MovieRequestDTO dto);

    void update(MovieUpdateDTO dto);

    void delete(Long id);

    Set<Movie> findActorMovies(Long actorId);

}
