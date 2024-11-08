package fiap.tds.apiodontoprev.models;

import fiap.tds.apiodontoprev.enums.TipoTelefone;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_TELEFONE")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefone")
    private Long id;

    @Column(name = "numero_telefone", length = 15, nullable = false)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_telefone", nullable = false)
    private TipoTelefone tipo;
}
