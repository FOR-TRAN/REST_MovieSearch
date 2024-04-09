package org.example.service;


import org.example.dto.request.ActorRequestDTO;
import org.example.dto.response.ActorResponseDTO;
import org.example.dto.update.ActorUpdateDTO;
import org.example.entity.Actor;

import java.util.Set;

public interface ActorService {
    ActorResponseDTO find(Long id);

    void save(ActorRequestDTO dto);

    void update(ActorUpdateDTO dto);

    void delete(Long id);

    void findMovie(Long actorId, Long movieId);

    Set<Actor> findMovieActors(Long movieId);

}
