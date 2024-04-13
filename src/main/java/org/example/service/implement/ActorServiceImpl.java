package org.example.service.implement;
import lombok.*;
import org.example.dto.request.ActorRequestDTO;
import org.example.dto.response.ActorResponseDTO;
import org.example.dto.update.ActorUpdateDTO;
import org.example.entity.Actor;
import org.example.repository.ActorRepository;
import org.example.repository.implement.ActorRepositoryImpl;
import org.example.service.ActorService;
import org.example.mapper.ActorMapper;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class ActorServiceImpl implements ActorService {

    private ActorRepository actorRepository = new ActorRepositoryImpl();

    @Override
    public ActorResponseDTO find(Long id) {
        Actor actor = actorRepository.find(id); //создается обьект entity actor и через репозиторий в него из БД записываются данные
        return ActorMapper.toDTO(actor); //посылаем entity обьект в маппер для преобразования в DTO обьект и возвращаем обьект ДТО
    }

    @Override
    public void save(ActorRequestDTO dto) {
        Actor actor = ActorMapper.toEntity(dto);
        actorRepository.save(actor);
    }

    @Override
    public void update(ActorUpdateDTO dto) {
        Actor actor = ActorMapper.toEntity(dto);
        actorRepository.update(actor);
    }

    @Override
    public void delete(Long id) {
        actorRepository.delete(id);
    }

    @Override
    public void findMovie(Long actorId, Long movieId) {
        actorRepository.findMovie(actorId, movieId);
    }

    @Override
    public Set<Actor> findMovieActors(Long movieId) {
        return actorRepository.findMovieActors(movieId);
    }
}
/*Слой репозитория используется для управления доступом к данным.
Этот слой обеспечивает абстракцию между бизнес-логикой приложения и базой данных.
 */