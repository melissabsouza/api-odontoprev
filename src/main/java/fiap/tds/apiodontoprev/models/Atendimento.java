package fiap.tds.apiodontoprev.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_ATENDIMENTO")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atendimento")
    private Long id;

    @Column(name = "tipo_procedimento", length = 100, nullable = false)
    private String tipoProcedimento;

    @Column(name = "descricao_atendimento", length = 150, nullable = false)
    private String descricao;

    @Column(name = "data_atendimento", nullable = false)
    private Date dataAtendimento;

    @Column(name = "custo_estimado", nullable = false)
    private float custoEstimado;

    @OneToOne
    @JoinColumn(name = "cpf_paciente", referencedColumnName = "cpf_paciente", nullable = false)
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "cpf_dentista", referencedColumnName = "cpf_dentista", nullable = false)
    private Dentista dentista;
}
