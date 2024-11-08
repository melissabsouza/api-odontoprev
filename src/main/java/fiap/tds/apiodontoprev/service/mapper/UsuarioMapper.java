package fiap.tds.apiodontoprev.service.mapper;

import fiap.tds.apiodontoprev.dto.UsuarioDTO;
import fiap.tds.apiodontoprev.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario usuario);
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
