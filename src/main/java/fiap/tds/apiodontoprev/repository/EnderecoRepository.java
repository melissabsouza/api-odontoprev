package fiap.tds.apiodontoprev.repository;

import fiap.tds.apiodontoprev.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findById(Long id);
}
