package fiap.tds.apiodontoprev.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_PACIENTE")
public class Paciente {
    @Id
    @Column(name = "cpf_paciente", unique = true, nullable = false)
    private String cpf;
    @Column(name = "nome_paciente", length = 100, nullable = false)
    private String nome;
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;
    @Column(name = "genero_paciente", length = 100, nullable = false)
    private String genero;

    @OneToOne
    @JoinColumn(name = "cnpj_clinica", referencedColumnName = "cnpj_clinica", nullable = false)
    private Clinica clinica;

    @OneToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco", nullable = false)
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "id_telefone", referencedColumnName = "id_telefone", nullable = false)
    private Telefone telefone;
}
