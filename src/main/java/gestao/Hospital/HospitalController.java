package gestao.Hospital;

import gestao.Hospital.Hospital;
import gestao.Hospital.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {
        @Autowired
        HospitalService hospitalService;


        @GetMapping
        public List<Hospital> index() {
            return this.hospitalService.findAll();
        }

        @GetMapping("/{id}")
        public Long show(@PathVariable(value = "id") Long id) {
            return id;
        }

        @PostMapping
        public void store(Hospital hospital) {
            this.hospitalService.create(hospital);
        }


        @PutMapping("/{id}")
        public ResponseEntity<String> update(@PathVariable(value = "id") Long id, Hospital hospital) {
            if (this.hospitalService.update(id, hospital)) {
                return ResponseEntity.ok().body("Hospital atualizado");
            }
            return ResponseEntity.badRequest().body(null);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String>  delete(@PathVariable(value = "id") Long id) {
            this.hospitalService.delete(id);
            return ResponseEntity.ok().body("Hospital deletado");
        }
}

