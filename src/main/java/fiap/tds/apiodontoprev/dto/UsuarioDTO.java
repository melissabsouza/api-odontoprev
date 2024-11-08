package fiap.tds.apiodontoprev.dto;

import fiap.tds.odontoprev_api.enums.StatusUsuario;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String email;
    private String senha;
    private StatusUsuario status = StatusUsuario.ATIVO;
}
