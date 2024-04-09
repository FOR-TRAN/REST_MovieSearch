package org.example.mapper;
import lombok.experimental.UtilityClass;
import org.example.dto.request.MovieRequestDTO;
import org.example.dto.response.CinemaResponseDTO;
import org.example.dto.response.MovieResponseDTO;
import org.example.dto.update.MovieUpdateDTO;
import org.example.entity.Movie;
import org.example.service.ActorService;
import org.example.service.implement.ActorServiceImpl;

/*@UtilityClass представляет собой экспериментальную аннотацию,
 которая позволяет создавать классы, содержащие только статические методы и константы,
  без неявного конструктора или возможности создать экземпляр этого класса.
   Данная аннотация генерирует приватный конструктор, делает все поля финальными и создает
    приватный статический внутренний класс, который содержит только статические методы
 */
@UtilityClass
public class MovieMapper {

    private final ActorService actorService = new ActorServiceImpl();

    public Movie toEntity(MovieRequestDTO dto) {
        return Movie.builder()
                .movieName(dto.getMovieName())
                .category(dto.getCategory())
                .cinemaId(dto.getCinemaId())
                .build();
    }

    public Movie toEntity(MovieUpdateDTO dto) {
        return Movie.builder()
                .id(dto.getId())
                .movieName(dto.getMovieName())
                .category(dto.getCategory())
                .cinemaId(dto.getCinemaId())
                .build();
    }

    public MovieResponseDTO toDTO(Movie movie, CinemaResponseDTO cinema) {
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .category(movie.getCategory())
                .cinema(cinema)
                .actors(actorService.findMovieActors(movie.getId()))
                .build();
    }
}
