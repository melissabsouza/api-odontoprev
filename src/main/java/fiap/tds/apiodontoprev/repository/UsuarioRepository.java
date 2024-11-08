package fiap.tds.apiodontoprev.repository;

import fiap.tds.apiodontoprev.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
     Optional<Usuario> findById(Long id);
}
