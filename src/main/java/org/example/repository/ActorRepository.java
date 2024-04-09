package org.example.repository;
import org.example.entity.Actor;

import java.util.Set;
public interface ActorRepository {
    Actor find(Long actorId);

    void save(Actor actor);

    void update(Actor actor);

    void delete(Long actorId);

    void findMovie(Long actorId, Long movieId);

    Set<Actor> findMovieActors(Long movieId);
}
