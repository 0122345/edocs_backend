package mid.ntwari.edocs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mid.ntwari.edocs.model.Signer;
import mid.ntwari.edocs.repository.SignerRepository;

@Service
public class SignerService {
    private final SignerRepository signerRepository;

    public SignerService(SignerRepository signerRepository) {
        this.signerRepository = signerRepository;
    }

    public Signer save(Signer signer) {
        return signerRepository.save(signer);
    }

    public List<Signer> findAll() {
        return signerRepository.findAll();
    }

    public Signer findById(UUID id) {
        if (signerRepository.existsById(id)) {
            return signerRepository.findById(id).get();
        }
        return null;
    }

    public Signer update(UUID id, Signer signer) {

        if (signerRepository.existsById(id)) {
            Signer found = signerRepository.findById(id).get();
            found.setFirstName(signer.getFirstName());
            found.setLastName(signer.getLastName());
            found.setNationalId(signer.getNationalId());
            found.setEmail(signer.getEmail());
            found.setOrganisation(signer.getOrganisation());

            return signerRepository.save(found);
        }
        return null;
    }

    public boolean delete(UUID id) {
        if (signerRepository.existsById(id)) {
            signerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Signer> findByLastName(String lastName) {
        return signerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public List<Signer> findByLegalOfficerId(UUID legalOfficerId) {
        return signerRepository.findByLegalOfficerId(legalOfficerId);
    }
}
