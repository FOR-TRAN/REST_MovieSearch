package service;

import org.example.dto.request.ActorRequestDTO;
import org.example.dto.response.ActorResponseDTO;
import org.example.dto.update.ActorUpdateDTO;
import org.example.entity.Actor;
import org.example.repository.ActorRepository;
import org.example.repository.implement.ActorRepositoryImpl;
import org.example.service.ActorService;
import org.example.service.implement.ActorServiceImpl;
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

public class ActorServiceImplTest   {

    private final ActorRepository mockActorRepository = Mockito.mock(ActorRepositoryImpl.class);
    private final ActorService actorService = new ActorServiceImpl(mockActorRepository);

    private ActorRequestDTO requestDTO;
    private ActorResponseDTO responseDTO;
    private ActorUpdateDTO updateDTO;
    private Actor actor;
    private Actor actorToSave;
    private Actor actorToUpdate;


    @BeforeEach
    void setUp() {
        requestDTO = ActorRequestDTO.builder()
                .actorName("Jim Carrey")
                .build();
        responseDTO = ActorResponseDTO.builder()
                .id(1L)
                .actorName("Jim Carrey")
                .movies(Collections.emptySet())
                .build();
        updateDTO = ActorUpdateDTO.builder()
                .id(1L)
                .actorName("John Travolta")
                .build();
        actor = Actor.builder()
                .id(1L)
                .actorName("Jim Carrey")
                .build();
        actorToSave = Actor.builder()
                .actorName("Jim Carrey")
                .build();
        actorToUpdate = Actor.builder()
                .id(1L)
                .actorName("John Travolta")
                .build();
    }

    @AfterEach
    void tearDown() {
        requestDTO = null;
        responseDTO = null;
        updateDTO = null;
        actor = null;
        actorToSave = null;
        actorToUpdate = null;
    }

    @ParameterizedTest
    @ValueSource(longs = {1, Long.MAX_VALUE})
    void findActorByIdThenReturnDTO(Long id) {
        Mockito
                .when(mockActorRepository.find(id))
                .thenReturn(actor);

        ActorResponseDTO result = actorService.find(id);
        Assertions.assertEquals(responseDTO, result);
    }

    @Test
    void saveActor() {
        actorService.save(requestDTO);
        verify(mockActorRepository, times(1)).save(actorToSave);
    }

    @Test
    void updateCustomer() {
        actorService.update(updateDTO);
        verify(mockActorRepository, times(1)).update(actorToUpdate);
    }

    @Test
    void delete() {
        actorService.delete(1L);
        verify(mockActorRepository, times(1)).delete(1L);
    }

    @Test
    void assignCoach() {
        actorService.findMovie(1L, 1L);
        verify(mockActorRepository, times(1)).findMovie(1L, 1L);
    }

    @Test
    void findMovieActors() {
        Set<Actor> actors = new HashSet<>();
        actors.add(actor);
        Mockito
                .when(mockActorRepository.findMovieActors(1L))
                .thenReturn(actors);

        Set<Actor> result = actorService.findMovieActors(1L);
        Assertions.assertEquals(actors, result);
    }
}
