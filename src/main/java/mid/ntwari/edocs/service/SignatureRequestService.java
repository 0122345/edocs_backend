package mid.ntwari.edocs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mid.ntwari.edocs.model.RequestStatus;
import mid.ntwari.edocs.model.SignatureRequest;
import mid.ntwari.edocs.repository.SignatureRequestRepository;

@Service
public class SignatureRequestService {
    private final SignatureRequestRepository signatureRequestRepository;

    public SignatureRequestService(
            SignatureRequestRepository signatureRequestRepository) {
        this.signatureRequestRepository = signatureRequestRepository;
    }

    public SignatureRequest save(SignatureRequest signatureRequest) {
        return signatureRequestRepository.save(signatureRequest);
    }

    public List<SignatureRequest> findAll() {
        return signatureRequestRepository.findAll();
    }

    public SignatureRequest findById(UUID id) {
        if (signatureRequestRepository.existsById(id)) {
            return signatureRequestRepository.findById(id).get();
        }
        return null;
    }

    public SignatureRequest update(
            UUID id, SignatureRequest signatureRequest) {

        if (signatureRequestRepository.existsById(id)) {

            SignatureRequest found = signatureRequestRepository.findById(id).get();

            found.setRequestDate(signatureRequest.getRequestDate());
            found.setStatus(signatureRequest.getStatus());
            found.setDocumentTitle(signatureRequest.getDocumentTitle());
            found.setSigner(signatureRequest.getSigner());
            found.setLegalOfficer(signatureRequest.getLegalOfficer());

            return signatureRequestRepository.save(found);
        }

        return null;
    }

    public boolean delete(UUID id) {
        if (signatureRequestRepository.existsById(id)) {
            signatureRequestRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<SignatureRequest> findByStatus(RequestStatus status) {
        return signatureRequestRepository.findByStatusOrderByRequestDateAsc(status);
    }
}
