package mid.ntwari.edocs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mid.ntwari.edocs.model.Signer;

public interface SignerRepository extends JpaRepository<Signer, UUID> {
    List<Signer> findByLastNameIgnoreCaseOrderByFirstnameAsc(String lastName);

    @Query("SELECT DISTINCT s FROM Signer s JOIN SignatureRequest r ON r.signer = s WHERE r.legalOfficer.id = :legalOfficerId")
    List<Signer> findByLegalOfficerId(@Param("legalOfficerId") UUID legalOfficerId);
}
