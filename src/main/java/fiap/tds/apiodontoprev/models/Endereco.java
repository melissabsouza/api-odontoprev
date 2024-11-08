package fiap.tds.apiodontoprev.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "rua_endereco", length = 100, nullable = false)
    private String rua;

    @Column(name = "numero_endereco", nullable = false)
    private int numero;

    @Column(name = "complemento_endereco", length = 100, nullable = false)
    private String complemento;

    @Column(name = "bairro_endereco", length = 100, nullable = false)
    private String bairro;

    @Column(name = "cidade_endereco", length = 100, nullable = false)
    private String cidade;

    @Column(name = "estado_endereco", length = 100, nullable = false)
    private String estado;

    @Column(name = "cep", nullable = false)
    private Long cep;

}
