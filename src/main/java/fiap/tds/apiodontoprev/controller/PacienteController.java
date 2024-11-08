package fiap.tds.apiodontoprev.controller;

import fiap.tds.apiodontoprev.dto.PacienteDTO;
import fiap.tds.apiodontoprev.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@AllArgsConstructor
public class PacienteController {


    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        List<PacienteDTO> pacientes = pacienteService.getAllPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping
    public ResponseEntity<?> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        try {
            PacienteDTO novoPaciente = pacienteService.savePaciente(pacienteDTO);
            return new ResponseEntity<>(novoPaciente, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Paciente já existente", HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/{cpf}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable String cpf, @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO updatedPaciente = pacienteService.updatePaciente(cpf, pacienteDTO);
        return ResponseEntity.ok(updatedPaciente);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<PacienteDTO> buscarPorCpf(@PathVariable String cpf) {
        Optional<PacienteDTO> pacienteDTO = pacienteService.buscarPorCpf(cpf);
        return pacienteDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deletePaciente(@PathVariable String cpf) {
        try {
            pacienteService.deletePaciente(cpf);
            return ResponseEntity.ok("Paciente deletado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar o paciente.");
        }
    }


}
