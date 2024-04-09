package service;


import org.example.dto.request.CinemaRequestDTO;
import org.example.dto.response.CinemaResponseDTO;
import org.example.dto.update.CinemaUpdateDTO;
import org.example.entity.Cinema;
import org.example.repository.CinemaRepository;
import org.example.repository.implement.CinemaRepositoryImpl;
import org.example.service.CinemaService;
import org.example.service.implement.CinemaServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
public class CinemaServiceImplTest {

    private final CinemaRepository mockCinemaRepository = Mockito.mock(CinemaRepositoryImpl.class);
    private final CinemaService cinemaService = new CinemaServiceImpl(mockCinemaRepository);
    private CinemaRequestDTO requestDTO;
    private CinemaResponseDTO responseDTO;
    private CinemaUpdateDTO updateDTO;
    private Cinema cinema;
    private Cinema cinemaToSave;
    private Cinema cinemaToUpdate;

    @BeforeEach
    void setUp() {
        requestDTO = CinemaRequestDTO.builder()
                .name("October")
                .city("Moscow")
                .build();
        responseDTO = CinemaResponseDTO.builder()
                .id(1L)
                .name("October")
                .city("Moscow")
                .build();
        updateDTO = CinemaUpdateDTO.builder()
                .id(1L)
                .name("October")
                .city("Lipetsk")
                .build();
        cinema = Cinema.builder()
                .id(1L)
                .name("October")
                .city("Moscow")
                .build();
        cinemaToSave = Cinema.builder()
                .name("October")
                .city("Moscow")
                .build();
        cinemaToUpdate = Cinema.builder()
                .id(1L)
                .name("October")
                .city("Lipetsk")
                .build();
    }

    @AfterEach
    void tearDown() {
        requestDTO = null;
        responseDTO = null;
        updateDTO = null;
        cinema = null;
        cinemaToSave = null;
        cinemaToUpdate = null;
    }

    @ParameterizedTest
    @ValueSource(longs = {1, Long.MAX_VALUE})
    void findMovieByIdThenReturnDTO(Long id) {
        Mockito
                .when(mockCinemaRepository.find(id))
                .thenReturn(cinema);

        CinemaResponseDTO result = cinemaService.find(id);
        Assertions.assertEquals(responseDTO, result);
    }

    @Test
    void saveMovie() {
        cinemaService.save(requestDTO);
        verify(mockCinemaRepository, times(1)).save(cinemaToSave);
    }

    @Test
    void updateCoach() {
        cinemaService.update(updateDTO);
        verify(mockCinemaRepository, times(1)).update(cinemaToUpdate);
    }

    @Test
    void delete() {
        cinemaService.delete(1L);
        verify(mockCinemaRepository, times(1)).delete(1L);
    }
}
