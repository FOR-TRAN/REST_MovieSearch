package org.example.repository;
import org.example.entity.Movie;

import java.util.Set;
public interface MovieRepository {

    Movie find(Long movieId);

    void save(Movie movie);

    void update(Movie movie);

    void delete(Long movieId);

    Set<Movie> findActorMovies(Long actorId);
}
