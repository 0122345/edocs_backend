package mid.ntwari.edocs.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import mid.ntwari.edocs.model.RequestStatus;
import mid.ntwari.edocs.model.SignatureRequest;

public interface SignatureRequestRepository extends JpaRepository<SignatureRequest, UUID> {
    List<SignatureRequest> findByStatusOrderByRequestDateAsc(RequestStatus status);
    List<SignatureRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);
    boolean existsByLegalOfficer_IdAndRequestDateAndStatusNot(
        UUID legalOfficerId, LocalDate requestDate, RequestStatus status
    );
}
