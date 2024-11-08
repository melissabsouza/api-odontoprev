package fiap.tds.apiodontoprev.dto;

import lombok.Data;

import java.util.Date;
@Data
public class PacienteDTO {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String genero;
    private ClinicaDTO clinica;
    private EnderecoDTO endereco;
    private TelefoneDTO telefone;
}
