package mid.ntwari.edocs.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

import mid.ntwari.edocs.model.LegalOfficer;
import mid.ntwari.edocs.repository.LegalOfficerRepository;

@Service
public class LegalOfficerService {
    private final LegalOfficerRepository legalOfficerRepository;

    public LegalOfficerService(LegalOfficerRepository legalOfficerRepository) {
        this.legalOfficerRepository = legalOfficerRepository;
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
}
