package fiap.tds.apiodontoprev.service;

import fiap.tds.apiodontoprev.dto.AtendimentoDTO;
import fiap.tds.apiodontoprev.models.*;
import fiap.tds.apiodontoprev.repository.AtendimentoRepository;
import fiap.tds.apiodontoprev.repository.DentistaRepository;
import fiap.tds.apiodontoprev.repository.PacienteRepository;
import fiap.tds.apiodontoprev.service.mapper.AtendimentoMapper;
import fiap.tds.apiodontoprev.service.mapper.DentistaMapper;
import fiap.tds.apiodontoprev.service.mapper.PacienteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtendimentoService {
    private AtendimentoRepository atendimentoRepository;
    private PacienteRepository pacienteRepository;
    private DentistaRepository dentistaRepository;

    private AtendimentoMapper atendimentoMapper;
    private PacienteMapper pacienteMapper;
    private DentistaMapper dentistaMapper;

    @Transactional
    public List<AtendimentoDTO> getAllAtendimentos(){
        return atendimentoRepository.findAll()
                .stream()
                .map(atendimentoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<AtendimentoDTO> buscarPorId(Long id){
        return atendimentoRepository.findById(id).map(atendimentoMapper::toDto);
    }

    public void deleteAtendimento(Long id) {
        Optional<Atendimento> atendimentoExistente = atendimentoRepository.findById(id);
        if (atendimentoExistente.isPresent()) {
            atendimentoRepository.delete(atendimentoExistente.get());
        } else {
            throw new IllegalArgumentException("Atendimento deletado");
        }
    }

    public Atendimento createAtendimento(AtendimentoDTO atendimentoDTO) {
        Paciente paciente = pacienteRepository.findByCpf(atendimentoDTO.getPaciente().getCpf())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Dentista dentista = dentistaRepository.findByCpf(atendimentoDTO.getDentista().getCpf())
                .orElseThrow(() -> new EntityNotFoundException("Dentista não encontrado"));


        Atendimento atendimento = atendimentoMapper.toEntity(atendimentoDTO);

        atendimento.setPaciente(paciente);
        atendimento.setDentista(dentista);

        return atendimentoRepository.save(atendimento);
    }

    public AtendimentoDTO updateAtendimento(Long id, AtendimentoDTO atendimentoDTO) {
        Atendimento atendimentoExistente = atendimentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Atendimento não encontrado"));


        Paciente paciente = pacienteRepository.findByCpf(atendimentoDTO.getPaciente().getCpf())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));


        Dentista dentista = dentistaRepository.findByCpf(atendimentoDTO.getDentista().getCpf())
                .orElseThrow(() -> new EntityNotFoundException("Dentista não encontrado"));


        atendimentoExistente.setCustoEstimado(atendimentoDTO.getCustoEstimado());
        atendimentoExistente.setTipoProcedimento(atendimentoDTO.getTipoProcedimento());
        atendimentoExistente.setCustoEstimado(atendimentoDTO.getCustoEstimado());
        atendimentoExistente.setDescricao(atendimentoDTO.getDescricao());
        atendimentoExistente.setPaciente(paciente);
        atendimentoExistente.setDentista(dentista);


        Atendimento updatedAtendimento = atendimentoRepository.save(atendimentoExistente);

        return atendimentoMapper.toDto(updatedAtendimento);
    }

}
