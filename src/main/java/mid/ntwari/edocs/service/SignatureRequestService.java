package mid.ntwari.edocs.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mid.ntwari.edocs.model.RequestStatus;
import mid.ntwari.edocs.model.SignatureRequest;
import mid.ntwari.edocs.repository.ArchiveEntryRepository;
import mid.ntwari.edocs.repository.SignatureRequestRepository;

@Service
public class SignatureRequestService {
    private final SignatureRequestRepository signatureRequestRepository;
    private final ArchiveEntryRepository archiveEntryRepository;

    public SignatureRequestService(
            SignatureRequestRepository signatureRequestRepository, ArchiveEntryRepository archiveEntryRepository) {
        this.signatureRequestRepository = signatureRequestRepository;
        this.archiveEntryRepository = archiveEntryRepository;
    }

    public String  save(SignatureRequest signatureRequest) {
        if (signatureRequest.getRequestDate().isBefore(LocalDate.now())) {
            return "Signature request date can not be in the past";
        }
        boolean alreadyExists = signatureRequestRepository.existsByLegalOfficer_IdAndRequestDateAndStatusNot(signatureRequest.getLegalOfficer().getId(), signatureRequest.getRequestDate(), RequestStatus.DECLINED);
        if (alreadyExists) {
            return "LegalOfficer already has a signature request on that date";
        }
        signatureRequest.setStatus(RequestStatus.DRAFT);
        signatureRequestRepository.save(signatureRequest);
        return "Signature quest saved successfully";
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
            found.setDocumentTitle(signatureRequest.getDocumentTitle());
            found.setSigner(signatureRequest.getSigner());
            found.setLegalOfficer(signatureRequest.getLegalOfficer());

            return signatureRequestRepository.save(found);
        }

        return null;
    }

    public String delete(UUID id) {
        if (!signatureRequestRepository.existsById(id)) {
            return "The signature request with that id does not exist";  
        }
        SignatureRequest signatureRequest = signatureRequestRepository.findById(id).get();

        if(archiveEntryRepository.existsBySignatureRequest(signatureRequest)) {
            return "That signature request has entries, delete them first";
        }
        
        signatureRequestRepository.deleteById(id);
        return "Signature request deleted successfully";
    }
    public List<SignatureRequest> findByStatus(RequestStatus status) {
        return signatureRequestRepository.findByStatusOrderByRequestDateAsc(status);
    }

    public List<SignatureRequest> findBetweenDates(LocalDate start, LocalDate end){
        return signatureRequestRepository.findByRequestDateBetweenOrderByRequestDateAsc(start, end);
    }

    public String updateStatus(UUID id, RequestStatus newStatus) {
        if(!signatureRequestRepository.existsById(id)){
            return "The signature request with that is does not exist";
        }

        SignatureRequest found = signatureRequestRepository.findById(id).get();
        RequestStatus currentStatus =  found.getStatus();

        if(currentStatus == RequestStatus.DRAFT) {
            if (newStatus != RequestStatus.SENT && newStatus != RequestStatus.DECLINED) {
                return "Cannot change status from" + currentStatus + "to" + newStatus;
            }
        } else if(currentStatus == RequestStatus.SENT) {
            if (newStatus !=  RequestStatus.SIGNED && newStatus != RequestStatus.DECLINED) {
                return "Cannot change status from" + currentStatus + "to" + newStatus;
            }
        } else {
            return "Cannot change status from" + currentStatus + "to" + newStatus;
        }
        found.setStatus(newStatus);
        signatureRequestRepository.save(found);
        return "Signature request status updated sucessfully";
    }
}
