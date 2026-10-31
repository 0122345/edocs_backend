package mid.ntwari.edocs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

import mid.ntwari.edocs.model.Branch;
import mid.ntwari.edocs.model.DocumentType;
import mid.ntwari.edocs.model.LegalOfficer;
import mid.ntwari.edocs.repository.BranchRepository;
import mid.ntwari.edocs.repository.DocumentTypeRepository;
import mid.ntwari.edocs.repository.LegalOfficerRepository;

@Service
public class LegalOfficerService {
    private final LegalOfficerRepository legalOfficerRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final BranchRepository branchRepository;

    public LegalOfficerService(LegalOfficerRepository legalOfficerRepository,BranchRepository branchRepository,
            DocumentTypeRepository documentTypeRepository) {
        this.legalOfficerRepository = legalOfficerRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.branchRepository = branchRepository;
    }

    public LegalOfficer save(LegalOfficer legalOfficer) {
        return legalOfficerRepository.save(legalOfficer);
    }

    public List<LegalOfficer> findAll() {
        return legalOfficerRepository.findAll();
    }

    public LegalOfficer findById(UUID id) {
        if (legalOfficerRepository.existsById(id)) {
            return legalOfficerRepository.findById(id).get();
        }
        return null;
    }

    public LegalOfficer update(UUID id, LegalOfficer legalOfficer) {
        if (legalOfficerRepository.existsById(id)) {
            LegalOfficer found = legalOfficerRepository.findById(id).get();
            found.setFirstName(legalOfficer.getFirstName());
            found.setLastName(legalOfficer.getLastName());
            found.setEmail(legalOfficer.getEmail());
            found.setBranch(legalOfficer.getBranch());
            found.setDocumentTypes(legalOfficer.getDocumentTypes());

            return legalOfficerRepository.save(found);
        }
        return null;
    }

    public boolean delete(UUID id) {
        if (legalOfficerRepository.existsById(id)) {
            legalOfficerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<LegalOfficer> findByDocumentTypeName(String name) {
        return legalOfficerRepository.findByDocumentTypeName(name);
    }

    public String addDocumentType(UUID legalOfficerId, UUID documentTypeId) {
        if (!legalOfficerRepository.existsById(legalOfficerId)) {
            return "The legal officer with that is does not exist";
        }
        if (!documentTypeRepository.existsById(documentTypeId)) {
            return "That document type does not exist";
        }

        LegalOfficer legalOfficer = legalOfficerRepository.findById(legalOfficerId).get();
        DocumentType documentType = documentTypeRepository.findById(documentTypeId).get();
        
        if (legalOfficer.getDocumentTypes() == null) {
            legalOfficer.setDocumentTypes(new ArrayList<>());
        }

        for (DocumentType existing : legalOfficer.getDocumentTypes()) {

            if (existing.getId().equals(documentTypeId)) {
                return "The legal officer already has that document type";
            }
        }

        legalOfficer.getDocumentTypes().add(documentType);
        legalOfficerRepository.save(legalOfficer);
        return "Document type added to the legal officer";

    }

    public String changeBranch(UUID legalOfficerId, int branchNumber) {
        if(!legalOfficerRepository.existsById(legalOfficerId)){
            return "The legal officer with that id does not exist";
        }
        LegalOfficer legalOfficer = legalOfficerRepository.findById(legalOfficerId).get();

    Branch branch = branchRepository.findByBranchNumber(branchNumber);

    if (branch == null) {
        return "The branch with that branchNumber does not exist";
    }

    if (legalOfficerRepository.existsByBranch(branch)) {
        if (legalOfficer.getBranch() == null || !legalOfficer.getBranch().getId().equals(branch.getId())) {
            return "That branch is taken by another legal officer";
        }
    }

    legalOfficer.setBranch(branch);
    legalOfficerRepository.save(legalOfficer);
    return "Branch changed successfully";

    }
}