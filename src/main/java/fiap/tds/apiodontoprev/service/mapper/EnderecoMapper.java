package fiap.tds.apiodontoprev.service.mapper;

import fiap.tds.apiodontoprev.dto.EnderecoDTO;
import fiap.tds.apiodontoprev.models.Endereco;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoDTO toDto(Endereco endereco);
    Endereco toEntity(EnderecoDTO enderecoDTO);
}
