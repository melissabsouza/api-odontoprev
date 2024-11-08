package fiap.tds.apiodontoprev.models;

import fiap.tds.odontoprev_api.enums.StatusUsuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "T_CHALLENGE_USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "email_usuario", length = 100, nullable = false)
    private String email;
    @Column(name = "senha_usuario", length = 100, nullable = false)
    private String senha;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_usuario", nullable = false)
    private StatusUsuario status = StatusUsuario.ATIVO;
}
