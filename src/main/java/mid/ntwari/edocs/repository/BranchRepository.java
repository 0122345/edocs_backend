package mid.ntwari.edocs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mid.ntwari.edocs.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, UUID>{
    
}
