package mid.ntwari.edocs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mid.ntwari.edocs.model.ArchiveEntry;
import mid.ntwari.edocs.model.SignatureRequest;

public interface ArchiveEntryRepository extends JpaRepository<ArchiveEntry, UUID>{
    @Query("SELECT a FROM ArchiveEntry a WHERE a.signatureRequest.signer.nationalId = :nationalId ORDER BY a.archiveDate DESC")
    List<ArchiveEntry> findBySignerNationalId(@Param("nationalId") String nationalId);

    boolean existsBySignatureRequest(SignatureRequest signatureRequest);
}
