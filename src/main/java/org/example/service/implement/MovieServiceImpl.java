package org.example.service.implement;
import lombok.*;
import org.example.dto.request.MovieRequestDTO;
import org.example.dto.response.CinemaResponseDTO;
import org.example.dto.response.MovieResponseDTO;
import org.example.dto.update.MovieUpdateDTO;
import org.example.entity.Movie;
import org.example.mapper.MovieMapper;
import org.example.repository.MovieRepository;
import org.example.repository.implement.MovieRepositoryImpl;
import org.example.service.CinemaService;
import org.example.service.MovieService;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class MovieServiceImpl implements MovieService  {

    private MovieRepository movieRepository = new MovieRepositoryImpl();
    private CinemaService cinemaService = new CinemaServiceImpl();

    @Override
    public MovieResponseDTO find(Long movieId) {
        Movie movie = movieRepository.find(movieId);
        CinemaResponseDTO cinemaDTO = cinemaService.find(movie.getCinemaId());
        return MovieMapper.toDTO(movie, cinemaDTO);
    }

    @Override
    public void save(MovieRequestDTO dto) {
        Movie movie = MovieMapper.toEntity(dto);
        movieRepository.save(movie);
    }

    @Override
    public void update(MovieUpdateDTO dto) {
        Movie movie = MovieMapper.toEntity(dto);
        movieRepository.update(movie);
    }

    @Override
    public void delete(Long movieId) {
        movieRepository.delete(movieId);
    }

    @Override
    public Set<Movie> findActorMovies(Long actorId) {
        return movieRepository.findActorMovies(actorId);

    }
}
