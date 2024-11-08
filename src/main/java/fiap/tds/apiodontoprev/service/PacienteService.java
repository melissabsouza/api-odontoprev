package fiap.tds.apiodontoprev.service;

import fiap.tds.apiodontoprev.dto.PacienteDTO;
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
public class PacienteService {

    private PacienteRepository pacienteRepository;
    private ClinicaRepository clinicaRepository;
    private EnderecoRepository enderecoRepository;
    private TelefoneRepository telefoneRepository;

    private PacienteMapper pacienteMapper;
    private ClinicaMapper clinicaMapper;
    private EnderecoMapper enderecoMapper;
    private TelefoneMapper telefoneMapper;


    @Transactional
    public List<PacienteDTO> getAllPacientes() {
        return pacienteRepository.findAll()
                .stream()
                .map(pacienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<PacienteDTO> buscarPorCpf(String cpf){
        return pacienteRepository.findByCpf(cpf).map(pacienteMapper::toDto);
    }

    public void deletePaciente(String cpf) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(cpf);
        if (pacienteExistente.isPresent()) {
            pacienteRepository.delete(pacienteExistente.get());
        } else {
            throw new IllegalArgumentException("Paciente não encontrado");
        }
    }

    public PacienteDTO savePaciente(PacienteDTO pacienteDTO) {

        if (pacienteDTO.getClinica() == null) {
            throw new IllegalArgumentException("Clínica não pode ser nula");
        }

        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(pacienteDTO.getCpf());
        if (pacienteExistente.isPresent()) {
            throw new IllegalArgumentException(" Paciente já existente");
        }

        Endereco endereco = enderecoMapper.toEntity(pacienteDTO.getEndereco());
        Endereco savedEndereco = enderecoRepository.save(endereco);

        Telefone telefone = telefoneMapper.toEntity(pacienteDTO.getTelefone());
        Telefone savedTelefone = telefoneRepository.save(telefone);

        Clinica clinica;
        Optional<Clinica> clinicaExistente = clinicaRepository.findByCnpj(pacienteDTO.getClinica().getCnpj());
        if (clinicaExistente.isPresent()) {
            clinica = clinicaExistente.get();
        } else {
            clinica = clinicaMapper.toEntity(pacienteDTO.getClinica());
            clinica = clinicaRepository.save(clinica);
        }

        Paciente paciente = pacienteMapper.toEntity(pacienteDTO);
        paciente.setClinica(clinica);
        paciente.setEndereco(savedEndereco);
        paciente.setTelefone(savedTelefone);

        Paciente savedPaciente = pacienteRepository.save(paciente);
        return pacienteMapper.toDto(savedPaciente);
    }

    public PacienteDTO updatePaciente(String cpf, PacienteDTO pacienteDTO){

        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(pacienteDTO.getCpf());
        if (pacienteExistente.isEmpty()) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }

        Paciente paciente = pacienteExistente.get();


        Endereco endereco = enderecoMapper.toEntity(pacienteDTO.getEndereco());
        Endereco savedEndereco = enderecoRepository.save(endereco);


        Telefone telefone = telefoneMapper.toEntity(pacienteDTO.getTelefone());
        Telefone savedTelefone = telefoneRepository.save(telefone);


        Clinica clinica;
        Optional<Clinica> clinicaExistente = clinicaRepository.findByCnpj(pacienteDTO.getClinica().getCnpj());
        if (clinicaExistente.isPresent()) {
            clinica = clinicaExistente.get();
        } else {
            throw new IllegalArgumentException("Clínica não encontrada");
        }


        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        paciente.setGenero(pacienteDTO.getGenero());

        paciente.setClinica(clinica);
        paciente.setEndereco(savedEndereco);
        paciente.setTelefone(savedTelefone);

        Paciente savedPaciente = pacienteRepository.save(paciente);

        return pacienteMapper.toDto(savedPaciente);
    }




}
