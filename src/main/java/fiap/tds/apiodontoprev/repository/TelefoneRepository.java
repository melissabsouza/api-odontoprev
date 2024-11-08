package fiap.tds.apiodontoprev.repository;

import fiap.tds.apiodontoprev.models.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    Optional<Telefone> findById(Long id);
}
