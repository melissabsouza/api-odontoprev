package fiap.tds.apiodontoprev.service.mapper;

import fiap.tds.apiodontoprev.dto.PacienteDTO;
import fiap.tds.apiodontoprev.models.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteDTO toDto(Paciente paciente);
    Paciente toEntity(PacienteDTO pacienteDTO);
}
