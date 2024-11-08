package fiap.tds.apiodontoprev.service.mapper;

import fiap.tds.apiodontoprev.dto.TelefoneDTO;
import fiap.tds.apiodontoprev.models.Telefone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {
    TelefoneDTO toDto(Telefone telefone);
    Telefone toEntity(TelefoneDTO telefoneDTO);
}
