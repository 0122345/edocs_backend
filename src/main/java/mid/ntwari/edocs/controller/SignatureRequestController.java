package mid.ntwari.edocs.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mid.ntwari.edocs.model.RequestStatus;
import mid.ntwari.edocs.model.SignatureRequest;
import mid.ntwari.edocs.service.SignatureRequestService;

@RestController
@RequestMapping("/signature-requests")
public class SignatureRequestController {
    private final SignatureRequestService signatureRequestService;

    public SignatureRequestController(SignatureRequestService signatureRequestService) {
        this.signatureRequestService = signatureRequestService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody SignatureRequest signatureRequest) {
        String result = signatureRequestService.save(signatureRequest);
        
        if(result.equals("Signature request date cannot be in the past")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } else if(result.equals("LegalOfficer already has a signature request on that date")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        } else{
        return ResponseEntity.status(HttpStatus.CREATED).body("Signature request saved successfully"); 
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SignatureRequest>> findAll() {
        return ResponseEntity.ok(signatureRequestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        SignatureRequest signatureRequest = signatureRequestService.findById(id);
        if (signatureRequest != null) {
            return ResponseEntity.ok(signatureRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The signature request with that id does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SignatureRequest signatureRequest) {

        SignatureRequest updatedSignatureRequest = signatureRequestService.update(id, signatureRequest);

        if (updatedSignatureRequest != null) {
            return ResponseEntity.ok(updatedSignatureRequest);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The signature request with that id does not exist");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        boolean deleted = signatureRequestService.delete(id);

        if (deleted) {
            return ResponseEntity.ok("Signature request deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The signature request with that id does not exist");
        }
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<SignatureRequest>> findByStatus(@RequestParam RequestStatus status) {
        return ResponseEntity.ok(signatureRequestService.findByStatus(status));
    }

    @GetMapping("/between")
    public ResponseEntity<List<SignatureRequest>> findBetweenDates(@RequestParam String start, @RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return ResponseEntity.ok(signatureRequestService.findBetweenDates(startDate, endDate));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable UUID id, @RequestParam RequestStatus status) {
        String result = signatureRequestService.updateStatus(id, status);

        if(result.equals("The signature request with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else if (result.equals("Signature request status updated successfully")){
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}
