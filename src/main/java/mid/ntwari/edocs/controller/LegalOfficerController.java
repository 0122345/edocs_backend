package mid.ntwari.edocs.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mid.ntwari.edocs.model.LegalOfficer;
import mid.ntwari.edocs.service.LegalOfficerService;

@RestController
@RequestMapping("/legal-officers")
public class LegalOfficerController {
    private final LegalOfficerService legalOfficerService;

    public LegalOfficerController(LegalOfficerService legalOfficerService) {
        this.legalOfficerService = legalOfficerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LegalOfficer legalOfficer) {
        legalOfficerService.save(legalOfficer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Legal Officer saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<LegalOfficer>> findAll() {
        return ResponseEntity.ok(legalOfficerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        LegalOfficer legalOfficer = legalOfficerService.findById(id);
        if (legalOfficer != null) {
            return ResponseEntity.ok(legalOfficer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The legal officer with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody LegalOfficer legalOfficer) {
        LegalOfficer updatedLegalOfficer = legalOfficerService.update(id, legalOfficer);
        if (updatedLegalOfficer != null) {
            return ResponseEntity.ok(updatedLegalOfficer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The legal Officer with taht id does not exit");
        }
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> delete(@PathVariable UUID id) {
        boolean deleted = legalOfficerService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Legal officer deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The legal Officer with that id does not exist");
        }
    }

    @GetMapping("/by-document-type")
    public ResponseEntity<List<LegalOfficer>> findByDocumentTypeName(@RequestParam String name) {
        return ResponseEntity.ok(legalOfficerService.findByDocumentTypeName(name));
    }
}
