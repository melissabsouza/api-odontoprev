package fiap.tds.apiodontoprev.service.mapper;

import fiap.tds.apiodontoprev.dto.AtendimentoDTO;
import fiap.tds.apiodontoprev.models.Atendimento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtendimentoMapper {
    AtendimentoDTO toDto(Atendimento atendimento);
    Atendimento toEntity(AtendimentoDTO atendimentoDTO);
}
