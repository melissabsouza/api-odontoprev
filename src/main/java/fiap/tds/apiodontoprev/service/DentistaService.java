package fiap.tds.apiodontoprev.service;

import fiap.tds.apiodontoprev.dto.DentistaDTO;
import fiap.tds.apiodontoprev.models.*;
import fiap.tds.apiodontoprev.repository.*;
import fiap.tds.apiodontoprev.service.mapper.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DentistaService {

    private DentistaRepository dentistaRepository;
    private ClinicaRepository clinicaRepository;
    private EnderecoRepository enderecoRepository;
    private TelefoneRepository telefoneRepository;

    private DentistaMapper dentistaMapper;
    private ClinicaMapper clinicaMapper;
    private EnderecoMapper enderecoMapper;
    private TelefoneMapper telefoneMapper;

    @Transactional
    public List<DentistaDTO> getAllDentistas() {
        return dentistaRepository.findAll()
                .stream()
                .map(dentistaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<DentistaDTO> buscarPorCpf(String cpf){
        return dentistaRepository.findByCpf(cpf).map(dentistaMapper::toDto);
    }

    public void deleteDentista(String cpf) {
        Optional<Dentista> dentistaExistente = dentistaRepository.findByCpf(cpf);
        if (dentistaExistente.isPresent()) {
            dentistaRepository.delete(dentistaExistente.get());
        } else {
            throw new IllegalArgumentException("Dentista não encontrado");
        }
    }

    public DentistaDTO saveDentista(DentistaDTO dentistaDTO) {

        if (dentistaDTO.getClinica() == null) {
            throw new IllegalArgumentException("Clínica não pode ser nula");
        }

        Optional<Dentista> dentistaExistente = dentistaRepository.findByCpf(dentistaDTO.getCpf());
        if (dentistaExistente.isPresent()) {
            throw new IllegalArgumentException("Dentista já existente");
        }

        Endereco endereco = enderecoMapper.toEntity(dentistaDTO.getEndereco());
        Endereco savedEndereco = enderecoRepository.save(endereco);

        Telefone telefone = telefoneMapper.toEntity(dentistaDTO.getTelefone());
        Telefone savedTelefone = telefoneRepository.save(telefone);

        Clinica clinica;
        Optional<Clinica> clinicaExistente = clinicaRepository.findByCnpj(dentistaDTO.getClinica().getCnpj());
        if (clinicaExistente.isPresent()) {
            clinica = clinicaExistente.get();
        } else {
            clinica = clinicaMapper.toEntity(dentistaDTO.getClinica());
            clinica = clinicaRepository.save(clinica);
        }

        Dentista dentista = dentistaMapper.toEntity(dentistaDTO);
        dentista.setClinica(clinica);
        dentista.setEndereco(savedEndereco);
        dentista.setTelefone(savedTelefone);

        Dentista savedDentista = dentistaRepository.save(dentista);
        return dentistaMapper.toDto(savedDentista);
    }

    public DentistaDTO updateDentista(String cpf, DentistaDTO dentistaDTO) {
        if (dentistaDTO.getClinica() == null) {
            throw new IllegalArgumentException("Clínica não pode ser nula");
        }

        // Verificar se o dentista já existe pelo CPF ou ID
        Optional<Dentista> dentistaExistente = dentistaRepository.findByCpf(dentistaDTO.getCpf());
        if (dentistaExistente.isEmpty()) {
            throw new IllegalArgumentException("Dentista não encontrado");
        }

        Dentista dentista = dentistaExistente.get();

        Endereco endereco = enderecoMapper.toEntity(dentistaDTO.getEndereco());
        Endereco savedEndereco = enderecoRepository.save(endereco);

        Telefone telefone = telefoneMapper.toEntity(dentistaDTO.getTelefone());
        Telefone savedTelefone = telefoneRepository.save(telefone);

        Clinica clinica;
        Optional<Clinica> clinicaExistente = clinicaRepository.findByCnpj(dentistaDTO.getClinica().getCnpj());
        if (clinicaExistente.isPresent()) {
            clinica = clinicaExistente.get();
        } else {
            throw new IllegalArgumentException("Clínica não encontrada");
        }


        dentista.setNome(dentistaDTO.getNome());
        dentista.setCpf(dentistaDTO.getCpf());
        dentista.setCro(dentista.getCro());
        dentista.setEspecialidade(dentistaDTO.getEspecialidade());
        dentista.setEmail(dentistaDTO.getEmail());
        dentista.setDataContratacao(dentistaDTO.getDataContratacao());

        dentista.setClinica(clinica);
        dentista.setEndereco(savedEndereco);
        dentista.setTelefone(savedTelefone);

        Dentista savedDentista = dentistaRepository.save(dentista);

        return dentistaMapper.toDto(savedDentista);
    }
}
