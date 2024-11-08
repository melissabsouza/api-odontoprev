package fiap.tds.apiodontoprev.repository;

import fiap.tds.apiodontoprev.models.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, String> {
    Optional<Clinica> findByCnpj(String cnpj);
}
