package mid.ntwari.edocs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mid.ntwari.edocs.model.DocumentType;

import java.util.List;
import java.util.UUID;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, UUID> {
    @Query("SELECT d FROM DocumentType d WHERE d.legalOfficers IS EMPTY")
    List<DocumentType> findUnusedDocumentTypes();
}
