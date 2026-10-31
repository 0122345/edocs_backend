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

import mid.ntwari.edocs.model.Signer;
import mid.ntwari.edocs.service.SignerService;

@RestController
@RequestMapping("/signers")
public class SignerController {
    private final SignerService signerService;

    public SignerController(SignerService signerService) {
        this.signerService = signerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Signer signer) {

        signerService.save(signer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Signer saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Signer>> findAll() {

        return ResponseEntity.ok(signerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        Signer signer = signerService.findById(id);

        if (signer != null) {
            return ResponseEntity.ok(signer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The signer with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Signer signer) {

        Signer updatedSigner = signerService.update(id, signer);

        if (updatedSigner != null) {
            return ResponseEntity.ok(updatedSigner);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The signer with that id does not exist");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        boolean deleted = signerService.delete(id);

        if (deleted) {
            return ResponseEntity.ok("Signer deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The signer with that id does not exist");
        }
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<List<Signer>> findByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(signerService.findByLastName(lastName));
    }
}
