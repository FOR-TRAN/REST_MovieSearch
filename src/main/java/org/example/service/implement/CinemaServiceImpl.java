package org.example.service.implement;
import lombok.*;
import org.example.dto.request.CinemaRequestDTO;
import org.example.dto.response.CinemaResponseDTO;
import org.example.dto.update.CinemaUpdateDTO;
import org.example.entity.Cinema;
import org.example.mapper.CinemaMapper;
import org.example.repository.CinemaRepository;
import org.example.repository.implement.CinemaRepositoryImpl;
import org.example.service.CinemaService;

@AllArgsConstructor
@NoArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private CinemaRepository cinemaRepository = new CinemaRepositoryImpl();

    @Override
    public CinemaResponseDTO find(Long cinemaId) {
        Cinema cinema = cinemaRepository.find(cinemaId);
        return CinemaMapper.toDTO(cinema);
    }

    @Override
    public void save(CinemaRequestDTO dto) {
        Cinema cinema = CinemaMapper.toEntity(dto);
        cinemaRepository.save(cinema);
    }

    @Override
    public void update(CinemaUpdateDTO dto) {
        Cinema cinema = CinemaMapper.toEntity(dto);
        cinemaRepository.update(cinema);
    }

    @Override
    public void delete(Long cinemaId) {
        cinemaRepository.delete(cinemaId);
    }

}
