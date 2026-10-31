package mid.ntwari.edocs.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mid.ntwari.edocs.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, UUID>{
    @Query("SELECT b FROM Branch b WHERE NOT EXISTS (SELECT l FROM LegalOfficer l WHERE l.branch = b) ORDER BY b.branchNumber ASC")
    List<Branch> findFreeBranches();
    Branch findByBranchNumber(int branchNumber);
}
