package gestao.controllers;

import gestao.models.hospital.Hospital;
import gestao.models.hospital.HospitalDTO;
import gestao.services.HospitalService;
import gestao.utils.Geolocalizacao.Ponto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitais")
@Api(value="API REST Hospitais")
@CrossOrigin(origins = "*")
public class HospitalController {

    @Autowired
    HospitalService service;


    @GetMapping
    @ApiOperation(value="Retorna uma lista de Hospitais")
    public ResponseEntity<List<Hospital>> index() {
        return this.index(1,10);
    }

    @GetMapping(params = {"page", "size"})
    @ApiOperation(value="Retorna uma lista de Hospitais")
    public ResponseEntity<List<Hospital>> index(@RequestParam("page") int page,
                                                @RequestParam("size") int size
    ) {
        return ResponseEntity.ok().body(service.findAll(PageRequest.of(page, size)).getContent());
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Retorna um Hospital")
    public ResponseEntity<Hospital> show(@PathVariable(value = "id") Long id) {
        Hospital hospital = this.service.find(id);
        return ResponseEntity.ok().body(hospital);
    }

    @PostMapping
    @ApiOperation(value="Salva um hospital.")
    public ResponseEntity<Hospital> store(@RequestBody HospitalDTO hospitalDTO) {
        Hospital hospital = this.service.create(hospitalDTO);
        return ResponseEntity.ok().body(hospital);

    }

    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza Hospital")
    public ResponseEntity<String> update(@PathVariable(value = "id") Long id, @RequestBody HospitalDTO hospital) {
            this.service.update(id, hospital);
            return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Remove um hospita.")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().body("Hospital deletado");
    }


    @GetMapping(value = "/encaminhamento")
    public ResponseEntity<List<Hospital>> findNearHospital(@Valid Ponto geocolocalizacao) {
        List<Hospital> hospitais = service.procurarPorHospitaisProximos(geocolocalizacao);
        return ResponseEntity.ok().body(hospitais);
    }


}

