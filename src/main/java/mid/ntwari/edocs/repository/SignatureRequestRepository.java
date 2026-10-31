package mid.ntwari.edocs.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import mid.ntwari.edocs.model.RequestStatus;
import mid.ntwari.edocs.model.SignatureRequest;

public interface SignatureRequestRepository extends JpaRepository<SignatureRequest, UUID> {
    List<SignatureRequest> findByStatusOrderByRequestDateAsc(RequestStatus status);
    List<SignatureRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);
    boolean existsByLegalOfficer_IdAndRequestDateAndStatusNot(
        UUID legalOfficerId, LocalDate requestDate, RequestStatus status
    );

    @Query("SELECT r.status, COUNT(r) FROM SignatureRequest r GROUP BY r.status")
    List<Object[]> countRequestsByStatus();

    @Modifying
    @Query("UPDATE SignatureRequest r SET r.status = :newStatus " + 
           "WHERE r.legalOfficer.id = :legalOfficerId " +
           "AND r.requestDate = :requestDate " + 
           "AND r.status <> :excludedStatus")
     int cancelRequestsForLegalOfficerOnDate(
        @Param("legalOfficerId") UUID legalOfficerId,
        @Param("requestDate") LocalDate requestDate,
        @Param("newStatus") RequestStatus newStatus,
        @Param("excludedStatus") RequestStatus excludedStatus
     );

     long countByRequestDate(LocalDate requestDate);

     long countByStatus(RequestStatus status);
}
