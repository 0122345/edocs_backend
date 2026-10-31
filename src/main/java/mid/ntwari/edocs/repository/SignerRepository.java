package mid.ntwari.edocs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mid.ntwari.edocs.model.Signer;

public interface SignerRepository extends JpaRepository<Signer, UUID> {

}
