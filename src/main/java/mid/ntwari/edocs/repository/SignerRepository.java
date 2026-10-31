package mid.ntwari.edocs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mid.ntwari.edocs.model.Signer;

public interface SignerRepository extends JpaRepository<Signer, UUID> {
    List<Signer> findByLastNameIgnoreCaseOrderByFirstnameAsc(String lastName);
}
