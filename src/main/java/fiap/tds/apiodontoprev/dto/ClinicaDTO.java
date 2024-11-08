package fiap.tds.apiodontoprev.dto;

import lombok.Data;

@Data
public class ClinicaDTO {
    private String cnpj;
    private String nome;
    private UsuarioDTO usuario;
    private EnderecoDTO endereco;
    private TelefoneDTO telefone;
}
