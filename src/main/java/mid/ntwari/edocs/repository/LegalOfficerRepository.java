package mid.ntwari.edocs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mid.ntwari.edocs.model.LegalOfficer;

public interface LegalOfficerRepository extends JpaRepository<LegalOfficer, UUID> {
    @Query("SELECT DISTINCT l FROM LegalOfficer l JOIN l.documentTypes d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<LegalOfficer> findByDocumentTypeName(@Param("name") String name);
}
