package mid.ntwari.edocs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mid.ntwari.edocs.model.DocumentType;
import mid.ntwari.edocs.repository.DocumentTypeRepository;

@Service 
public class DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;
    public DocumentTypeService(DocumentTypeRepository documentTypeRepository){this.documentTypeRepository = documentTypeRepository;}
    public DocumentType save(DocumentType documentType){return documentTypeRepository.save(documentType);}
    public List<DocumentType> findAll(){
        return documentTypeRepository.findAll();
    }
    public DocumentType findById(UUID id){
        if(documentTypeRepository.existsById(id)) {
            return documentTypeRepository.findById(id).get();
        }
        return null;
    }
    public DocumentType update(UUID id, DocumentType documentType) {
        if (documentTypeRepository.existsById(id)) {
            DocumentType found = documentTypeRepository.findById(id).get();
            found.setName(documentType.getName());
            return documentTypeRepository.save(found);
        }
        return null;
    }
    public boolean delete(UUID id) {
        if(documentTypeRepository.existsById(id)) {
            documentTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DocumentType> findUnusedDocumentTypes() {
        return documentTypeRepository.findUnusedDocumentTypes();
    }
}
