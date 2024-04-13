package org.example.mapper;
import lombok.experimental.UtilityClass;
import org.example.dto.request.ActorRequestDTO;
import org.example.dto.response.ActorResponseDTO;
import org.example.dto.update.ActorUpdateDTO;
import org.example.entity.Actor;
import org.example.service.MovieService;
import org.example.service.implement.MovieServiceImpl;

/*@UtilityClass представляет собой экспериментальную аннотацию,
 которая позволяет создавать классы, содержащие только статические методы и константы,
  без неявного конструктора или возможности создать экземпляр этого класса.
   Данная аннотация генерирует приватный конструктор, делает все поля финальными и создает
    приватный статический внутренний класс, который содержит только статические методы
 */

/*Mapper преобразует DTO обьекты (которые передают данные между слоями приложения)
в обьект Entity (который отображает данные из БД). Entity нельзя передавать между слоями напрямую,
чтобы ограничить доступ к конкретным данным, так же DTO передают данные которые нужны для конкретного сценария
чтобы упростить их структуру и не передавать лишнего
 */
@UtilityClass
public class ActorMapper {

    private MovieService movieService = new MovieServiceImpl();

    public Actor toEntity(ActorRequestDTO dto) {
        return Actor.builder()
                .actorName(dto.getActorName())
                .build();
    }

    public Actor toEntity(ActorUpdateDTO dto) {
        return Actor.builder()
                .id(dto.getId())
                .actorName(dto.getActorName())
                .build();
    }

    public ActorResponseDTO toDTO(Actor actor) {
        return ActorResponseDTO.builder()
                .id(actor.getId())
                .actorName(actor.getActorName())
                .movies(movieService.findActorMovies(actor.getId()))
                .build();
    }
}
