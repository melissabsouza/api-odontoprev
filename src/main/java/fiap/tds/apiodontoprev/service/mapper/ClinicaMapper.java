package fiap.tds.apiodontoprev.service.mapper;

import fiap.tds.apiodontoprev.dto.ClinicaDTO;
import fiap.tds.apiodontoprev.models.Clinica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClinicaMapper {
    ClinicaDTO toDto(Clinica clinica);
    Clinica toEntity(ClinicaDTO clinicaDTO);
}
