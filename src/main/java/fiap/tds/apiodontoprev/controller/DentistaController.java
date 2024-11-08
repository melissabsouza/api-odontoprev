package fiap.tds.apiodontoprev.controller;

import fiap.tds.apiodontoprev.dto.DentistaDTO;
import fiap.tds.apiodontoprev.service.DentistaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentistas")
@AllArgsConstructor
public class DentistaController {
    private DentistaService dentistaService;

    @GetMapping
    public ResponseEntity<List<DentistaDTO>> getAllDentistas() {
        List<DentistaDTO> dentistas = dentistaService.getAllDentistas();
        return ResponseEntity.ok(dentistas);
    }

    @PostMapping
    public ResponseEntity<?> createDentista(@RequestBody DentistaDTO dentistaDTO) {
        try {
            DentistaDTO novoDentista = dentistaService.saveDentista(dentistaDTO);
            return new ResponseEntity<>(novoDentista, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Dentista já existente", HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/{cpf}")
    public ResponseEntity<DentistaDTO> updateDentista(@PathVariable String cpf, @RequestBody DentistaDTO dentistaDTO) {
        DentistaDTO updatedDentista = dentistaService.updateDentista(cpf, dentistaDTO);
        return ResponseEntity.ok(updatedDentista);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<DentistaDTO> buscarPorCpf(@PathVariable String cpf) {
        Optional<DentistaDTO> dentistaDTO = dentistaService.buscarPorCpf(cpf);
        return dentistaDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deleteDentista(@PathVariable String cpf) {
        try {
            dentistaService.deleteDentista(cpf);
            return ResponseEntity.ok("Dentista deletado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar o Dentista");
        }
    }
}
