package fiap.tds.apiodontoprev.repository;

import fiap.tds.apiodontoprev.models.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DentistaRepository extends JpaRepository<Dentista, String> {

    Optional<Dentista> findByCpf(String cpf);
}
