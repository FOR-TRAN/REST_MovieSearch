package service;

import org.example.dto.request.MovieRequestDTO;
import org.example.dto.response.MovieResponseDTO;
import org.example.dto.update.MovieUpdateDTO;
import org.example.entity.Movie;
import org.example.repository.MovieRepository;
import org.example.repository.implement.MovieRepositoryImpl;
import org.example.service.CinemaService;
import org.example.service.MovieService;
import org.example.service.implement.CinemaServiceImpl;
import org.example.service.implement.MovieServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
public class MovieServiceImplTest {

    private final MovieRepository mockMovieRepository = Mockito.mock(MovieRepositoryImpl.class);
    private final CinemaService cinemaService = Mockito.mock(CinemaServiceImpl.class);
    private final MovieService movieService = new MovieServiceImpl(mockMovieRepository, cinemaService);
    private MovieRequestDTO requestDTO;
    private MovieResponseDTO responseDTO;
    private MovieUpdateDTO updateDTO;
    private Movie movie;
    private Movie movieToSave;
    private Movie movieToUpdate;

    @BeforeEach
    void setUp() {
        requestDTO = MovieRequestDTO.builder()
                .movieName("The mask")
                .category("Comedy")
                .cinemaId(1L)
                .build();
        responseDTO = MovieResponseDTO.builder()
                .id(1L)
                .movieName("The mask")
                .category("Comedy")
                .cinema(null)
                .actors(Collections.emptySet())
                .build();
        updateDTO = MovieUpdateDTO.builder()
                .id(1L)
                .movieName("Faceless")
                .category("Detective")
                .cinemaId(1L)
                .build();
        movie = Movie.builder()
                .id(1L)
                .movieName("The mask")
                .category("Comedy")
                .cinemaId(1L)
                .build();
        movieToSave = Movie.builder()
                .movieName("The mask")
                .category("Comedy")
                .cinemaId(1L)
                .build();
        movieToUpdate = Movie.builder()
                .id(1L)
                .movieName("Faceless")
                .category("Detective")
                .cinemaId(1L)
                .build();
    }

    @AfterEach
    void tearDown() {
        requestDTO = null;
        responseDTO = null;
        updateDTO = null;
        movie = null;
        movieToSave = null;
        movieToUpdate = null;
    }

    @ParameterizedTest
    @ValueSource(longs = {1, Long.MAX_VALUE})
    void findMovieByIdThenReturnDTO(Long id) {
        Mockito
                .when(mockMovieRepository.find(id))
                .thenReturn(movie);

        MovieResponseDTO result = movieService.find(id);
        Assertions.assertEquals(responseDTO, result);
    }

    @Test
    void saveMovie() {
        movieService.save(requestDTO);
        verify(mockMovieRepository, times(1)).save(movieToSave);
    }

    @Test
    void updateCoach() {
        movieService.update(updateDTO);
        verify(mockMovieRepository, times(1)).update(movieToUpdate);
    }

    @Test
    void delete() {
        movieService.delete(1L);
        verify(mockMovieRepository, times(1)).delete(1L);
    }

    @Test
    void findCustomerCoaches() {
        Set<Movie> actors = new HashSet<>();
        actors.add(movie);
        Mockito
                .when(mockMovieRepository.findActorMovies(1L))
                .thenReturn(actors);

        Set<Movie> result = movieService.findActorMovies(1L);
        Assertions.assertEquals(actors, result);
    }
}
