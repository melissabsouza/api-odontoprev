package fiap.tds.apiodontoprev.controller;

import fiap.tds.apiodontoprev.dto.ClinicaDTO;
import fiap.tds.apiodontoprev.service.ClinicaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clinicas")
@AllArgsConstructor
public class ClinicaController {
    private final ClinicaService clinicaService;

    @GetMapping
    public ResponseEntity<List<ClinicaDTO>> getAllClinicas() {
        return ResponseEntity.ok(clinicaService.getAllClinicas());
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<ClinicaDTO> buscarPorCnpj(@PathVariable String cnpj) {
        Optional<ClinicaDTO>  clinicaDTO = clinicaService.buscarPorCnpj(cnpj);
        return clinicaDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deleteClinica(@PathVariable String cnpj) {

        clinicaService.deleteClinica(cnpj);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<ClinicaDTO> updateClinica(@PathVariable String cnpj, @RequestBody ClinicaDTO clinicaDTO) {
        ClinicaDTO updatedClinica = clinicaService.updateClinica(cnpj, clinicaDTO);
        return ResponseEntity.ok(updatedClinica);
    }

    @PostMapping
    public ResponseEntity<?> createClinica(@RequestBody ClinicaDTO clinicaDTO) {
        try {
            ClinicaDTO novaClinica = clinicaService.saveClinica(clinicaDTO);
            return new ResponseEntity<>(novaClinica, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Clínica já existente", HttpStatus.CONFLICT);
        }

    }

}
