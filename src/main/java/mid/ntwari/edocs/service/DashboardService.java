package mid.ntwari.edocs.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import mid.ntwari.edocs.model.RequestStatus;
import mid.ntwari.edocs.repository.ArchiveEntryRepository;
import mid.ntwari.edocs.repository.LegalOfficerRepository;
import mid.ntwari.edocs.repository.SignatureRequestRepository;
import mid.ntwari.edocs.repository.SignerRepository;

@Service
public class DashboardService {
    private final SignatureRequestRepository signatureRequestRepository;
    private final LegalOfficerRepository legalOfficerRepository;
    private final SignerRepository signerRepository;
    private final ArchiveEntryRepository archiveEntryRepository;
    private final BranchService branchService;

    public DashboardService(SignerRepository signerRepository, LegalOfficerRepository legalOfficerRepository,
        SignatureRequestRepository signatureRequestRepository, ArchiveEntryRepository archiveEntryRepository,
        BranchService branchService
    ){
        this.signerRepository = signerRepository;
        this.legalOfficerRepository = legalOfficerRepository;
        this.signatureRequestRepository = signatureRequestRepository;
        this.archiveEntryRepository = archiveEntryRepository;
        this.branchService = branchService;
    }

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("signers", signerRepository.count());
        summary.put("legalOfficers", legalOfficerRepository.count());
        summary.put("freeBranches", (long) branchService.findFreeBranches().size());
        summary.put("requestsToday", signatureRequestRepository.countByRequestDate(LocalDate.now()));
        summary.put("draft", signatureRequestRepository.countByStatus(RequestStatus.DRAFT));
        summary.put("archiveEntries", archiveEntryRepository.count());
        return summary;
    }

}
