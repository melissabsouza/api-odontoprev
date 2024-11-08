package fiap.tds.apiodontoprev.dto;

import fiap.tds.apiodontoprev.enums.TipoTelefone;
import lombok.Data;

@Data
public class TelefoneDTO {

    private Long id;
    private String numero;
    private TipoTelefone tipo;
}
