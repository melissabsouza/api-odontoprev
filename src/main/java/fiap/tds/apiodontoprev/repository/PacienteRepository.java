package fiap.tds.apiodontoprev.repository;

import fiap.tds.apiodontoprev.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {
    Optional<Paciente> findByCpf(String cpf);
}
