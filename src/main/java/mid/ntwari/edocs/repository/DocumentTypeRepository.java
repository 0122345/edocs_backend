package mid.ntwari.edocs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import mid.ntwari.edocs.model.DocumentType;
import java.util.UUID;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, UUID> {
    
}
