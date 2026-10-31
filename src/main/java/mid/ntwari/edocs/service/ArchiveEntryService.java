package mid.ntwari.edocs.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mid.ntwari.edocs.model.ArchiveEntry;
import mid.ntwari.edocs.model.RequestStatus;
import mid.ntwari.edocs.model.SignatureRequest;
import mid.ntwari.edocs.repository.ArchiveEntryRepository;
import mid.ntwari.edocs.repository.SignatureRequestRepository;

@Service
public class ArchiveEntryService {
    private final ArchiveEntryRepository archiveEntryRepository;
    private final SignatureRequestRepository signatureRequestRepository;

    public ArchiveEntryService(ArchiveEntryRepository archiveEntryRepository, SignatureRequestRepository signatureRequestRepository){
        this.archiveEntryRepository = archiveEntryRepository;
        this.signatureRequestRepository = signatureRequestRepository;
    }

    public String save(UUID signatureRequestId, ArchiveEntry archiveEntry) {
        if(!signatureRequestRepository.existsById(signatureRequestId)) {
            return "The Signature request with that id does not exists";
        }

        SignatureRequest signatureRequest = signatureRequestRepository.findById(signatureRequestId).get();

        if(signatureRequest.getStatus() != RequestStatus.SIGNED) {
            return "Archive entries are only for SIGNED signature requests";
        }
        archiveEntry.setArchiveDate(LocalDate.now());
        archiveEntry.setSignatureRequest(signatureRequest);
        archiveEntryRepository.save(archiveEntry);

        return "Archive entry saved successfully";
    }

    public List<ArchiveEntry> findBySignerNationalId(String nationalId) {
        return archiveEntryRepository.findBySignerNationalId(nationalId);
    }

    public String delete(UUID id) {
        if(!archiveEntryRepository.existsById(id)) {
            return "The archive entry with that id does not exist";
        }
        archiveEntryRepository.deleteById(id);
        return "Archive entry deleted sucessfully";
    }
}
