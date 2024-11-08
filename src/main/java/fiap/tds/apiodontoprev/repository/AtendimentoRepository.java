package fiap.tds.apiodontoprev.repository;

import fiap.tds.apiodontoprev.models.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    Optional<Atendimento> findById(Long id);
}
