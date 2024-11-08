package fiap.tds.apiodontoprev.dto;


import lombok.Data;

import java.util.Date;

@Data
public class DentistaDTO {
    private String cpf;
    private String nome;
    private String cro;
    private String especialidade;
    private String email;
    private Date dataContratacao;
    private ClinicaDTO clinica;
    private EnderecoDTO endereco;
    private TelefoneDTO telefone;
}
