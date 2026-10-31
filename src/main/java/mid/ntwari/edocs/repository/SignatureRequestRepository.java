package mid.ntwari.edocs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mid.ntwari.edocs.model.RequestStatus;
import mid.ntwari.edocs.model.SignatureRequest;

public interface SignatureRequestRepository extends JpaRepository<SignatureRequest, UUID> {
    List<SignatureRequest> findByStatusOrderByRequestDateAsc(RequestStatus status);
}
