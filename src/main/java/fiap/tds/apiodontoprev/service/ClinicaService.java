package fiap.tds.apiodontoprev.service;

import fiap.tds.apiodontoprev.dto.ClinicaDTO;
import fiap.tds.apiodontoprev.models.Clinica;
import fiap.tds.apiodontoprev.models.Endereco;
import fiap.tds.apiodontoprev.models.Telefone;
import fiap.tds.apiodontoprev.models.Usuario;
import fiap.tds.apiodontoprev.repository.ClinicaRepository;
import fiap.tds.apiodontoprev.repository.EnderecoRepository;
import fiap.tds.apiodontoprev.repository.TelefoneRepository;
import fiap.tds.apiodontoprev.repository.UsuarioRepository;
import fiap.tds.apiodontoprev.service.mapper.ClinicaMapper;
import fiap.tds.apiodontoprev.service.mapper.EnderecoMapper;
import fiap.tds.apiodontoprev.service.mapper.TelefoneMapper;
import fiap.tds.apiodontoprev.service.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClinicaService {

    private ClinicaRepository clinicaRepository;
    private UsuarioRepository usuarioRepository;
    private EnderecoRepository enderecoRepository;
    private TelefoneRepository telefoneRepository;

    private ClinicaMapper clinicaMapper;
    private UsuarioMapper usuarioMapper;
    private EnderecoMapper enderecoMapper;
    private TelefoneMapper telefoneMapper;

    @Transactional
    public List<ClinicaDTO> getAllClinicas(){
        return clinicaRepository.findAll()
                .stream()
                .map(clinicaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ClinicaDTO> buscarPorCnpj(String cnpj){
        return clinicaRepository.findByCnpj(cnpj).map(clinicaMapper::toDto);
    }

    public void deleteClinica(String cnpj) {
        Optional<Clinica> clinicaExistente = clinicaRepository.findByCnpj(cnpj);
        if (clinicaExistente.isPresent()) {
            clinicaRepository.delete(clinicaExistente.get());
        } else {
            throw new IllegalArgumentException("Clínica não encontrada");
        }
    }


    public ClinicaDTO saveClinica(ClinicaDTO clinicaDTO) {
        Optional<Clinica> clinicaExistente = clinicaRepository.findByCnpj(clinicaDTO.getCnpj());
        if (clinicaExistente.isPresent()) {
            throw new IllegalArgumentException("Clínica já existente");
        }

        Usuario usuario = usuarioMapper.toEntity(clinicaDTO.getUsuario());
        Usuario savedUsuario = usuarioRepository.save(usuario);


        Endereco endereco = enderecoMapper.toEntity(clinicaDTO.getEndereco());
        Endereco savedEndereco = enderecoRepository.save(endereco);

        Telefone telefone = telefoneMapper.toEntity(clinicaDTO.getTelefone());
        Telefone savedTelefone = telefoneRepository.save(telefone);


        Clinica clinica = clinicaMapper.toEntity(clinicaDTO);
        clinica.setUsuario(savedUsuario);
        clinica.setEndereco(savedEndereco);
        clinica.setTelefone(savedTelefone);

        Clinica savedClinica = clinicaRepository.save(clinica);
        return clinicaMapper.toDto(savedClinica);
    }

    public ClinicaDTO updateClinica(String cnpj, ClinicaDTO clinicaDTO) {
        Clinica clinicaExistente = clinicaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new IllegalArgumentException("Clínica não encontrada com cnpj: " + cnpj));

        // Atualizar os dados do usuário, endereço e telefone associados à clínica
        Usuario usuario = usuarioMapper.toEntity(clinicaDTO.getUsuario());
        usuario.setId(clinicaExistente.getUsuario().getId());
        Usuario updatedUsuario = usuarioRepository.save(usuario);

        Endereco endereco = enderecoMapper.toEntity(clinicaDTO.getEndereco());
        endereco.setId(clinicaExistente.getEndereco().getId());
        Endereco updatedEndereco = enderecoRepository.save(endereco);

        Telefone telefone = telefoneMapper.toEntity(clinicaDTO.getTelefone());
        telefone.setId(clinicaExistente.getTelefone().getId());
        Telefone updatedTelefone = telefoneRepository.save(telefone);


        clinicaExistente.setNome(clinicaDTO.getNome());
        clinicaExistente.setCnpj(clinicaDTO.getCnpj());
        clinicaExistente.setUsuario(updatedUsuario);
        clinicaExistente.setEndereco(updatedEndereco);
        clinicaExistente.setTelefone(updatedTelefone);

        Clinica updatedClinica = clinicaRepository.save(clinicaExistente);
        return clinicaMapper.toDto(updatedClinica);
    }
}
