package mid.ntwari.edocs.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mid.ntwari.edocs.model.ArchiveEntry;
import mid.ntwari.edocs.service.ArchiveEntryService;

@RestController 
@RequestMapping("/archive-entries")
public class ArchiveEntryController {
    private final ArchiveEntryService archiveEntryService;

    public ArchiveEntryController(ArchiveEntryService archiveEntryService){
        this.archiveEntryService = archiveEntryService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID signatureRequestId, @RequestBody ArchiveEntry archiveEntry) {
        String result = archiveEntryService.save(signatureRequestId, archiveEntry);

        if(result.equals("The Signature request with that id does not exists")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else if (result.equals("Archive entries are only for SIGNED signature requests")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }

    }

    @GetMapping("/signer/{nationalId}")
    public ResponseEntity<List<ArchiveEntry>> findBySignerNationalId(@PathVariable String nationalId) {
        return ResponseEntity.ok(archiveEntryService.findBySignerNationalId(nationalId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String result = archiveEntryService.delete(id);

        if(result.equals("The archive entry with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
