package fiap.tds.apiodontoprev.service.mapper;

import fiap.tds.apiodontoprev.dto.DentistaDTO;
import fiap.tds.apiodontoprev.models.Dentista;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DentistaMapper {
    DentistaDTO toDto(Dentista dentista);
    Dentista toEntity(DentistaDTO dentistaDTO);
}
