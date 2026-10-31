package mid.ntwari.edocs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mid.ntwari.edocs.model.Branch;
import mid.ntwari.edocs.repository.BranchRepository;

@Service 
public class BranchService {
    private final BranchRepository branchRepository;
    public BranchService(BranchRepository branchRepository){
        this.branchRepository = branchRepository;
    }

    public Branch save(Branch branch) {return branchRepository.save(branch);}
    public List<Branch> findAll() {
        return branchRepository.findAll();
    }
    public Branch findById(UUID id){
        if (branchRepository.existsById(id)) {
            return branchRepository.findById(id).get();
        }
        return null;
    }

    public Branch update(UUID id, Branch branch) {
        if(branchRepository.existsById(id)) {
            Branch found = branchRepository.findById(id).get();
            found.setBranchNumber(branch.getBranchNumber());
            found.setName(branch.getName());
            return branchRepository.save(found);
        } else {
            return null;
        }
    }
    public boolean delete(UUID id) {
        if(branchRepository.existsById(id)) {
            branchRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Branch> findFreeBranches() {
        return branchRepository.findFreeBranches();
    }
}
