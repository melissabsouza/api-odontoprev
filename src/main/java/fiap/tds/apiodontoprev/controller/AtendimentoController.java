package fiap.tds.apiodontoprev.controller;

import fiap.tds.apiodontoprev.dto.AtendimentoDTO;
import fiap.tds.apiodontoprev.models.Atendimento;
import fiap.tds.apiodontoprev.service.AtendimentoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atendimentos")
@AllArgsConstructor
public class AtendimentoController {
    private final AtendimentoService atendimentoService;

    @GetMapping
    public ResponseEntity<List<AtendimentoDTO>> getAllAtendimentos() {
        return ResponseEntity.ok(atendimentoService.getAllAtendimentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> buscarPorId(@PathVariable Long id) {
        Optional<AtendimentoDTO> atendimentoDTO = atendimentoService.buscarPorId(id);
        return atendimentoDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAtendimento(@PathVariable Long id) {
        try {
            atendimentoService.deleteAtendimento(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Atendimento deletado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar Atendimento");
        }
    }

    @PostMapping
    public ResponseEntity<Atendimento> createAtendimento(@RequestBody AtendimentoDTO atendimentoDTO) {
        try {
            Atendimento novoAtendimento = atendimentoService.createAtendimento(atendimentoDTO);
            return new ResponseEntity<>(novoAtendimento, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> updateAtendimento(@PathVariable Long id, @RequestBody AtendimentoDTO atendimentoDTO) {
        AtendimentoDTO updatedAtendimento = atendimentoService.updateAtendimento(id, atendimentoDTO);
        return ResponseEntity.ok(updatedAtendimento);
    }
}
