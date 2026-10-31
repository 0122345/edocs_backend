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
import org.springframework.web.bind.annotation.RestController;

import mid.ntwari.edocs.model.DocumentType;
import mid.ntwari.edocs.service.DocumentTypeService;

@RestController
@RequestMapping("/document-types")
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody DocumentType documentType) {
        documentTypeService.save(documentType);
        return ResponseEntity.status(HttpStatus.CREATED).body("Document type saved sucessfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentType>> findAll() {
        return ResponseEntity.ok(documentTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        DocumentType documentType = documentTypeService.findById(id);
        if(documentType != null) {
            return ResponseEntity.ok(documentType);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The document type with this id does not exist");
        }
    }

    @PutMapping("/update/{id}")   
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody DocumentType documentType) {
        DocumentType updateDocumentType = documentTypeService.update(id, documentType);
        if(updateDocumentType != null){
            return ResponseEntity.ok(updateDocumentType);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The document type with that is does not exist");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        boolean deleted = documentTypeService.delete(id);
        if(deleted) {
            return ResponseEntity.ok("Document type deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The document type with that is does not exist");
        }
    }

    @GetMapping("/unused")
    public ResponseEntity<List<DocumentType>> findUnusedDocumentTypes() {
        return ResponseEntity.ok(documentTypeService.findUnusedDocumentTypes());
    }
}
