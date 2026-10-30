package mid.ntwari.edocs.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "signature_request")
public class SignatureRequest {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate requestDate;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    private String documentTitle;
    @ManyToOne
    @JoinColumn(name="signer_id")
    private Signer signer;
    @ManyToOne
    @JoinColumn(name="legal_officer_id")
    private LegalOfficer legalOfficer;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
	public RequestStatus getStatus() {
		return status;
	}
	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public Signer getSigner() {
		return signer;
	}
	public void setSigner(Signer signer) {
		this.signer = signer;
	}
	public LegalOfficer getLegalOfficer() {
		return legalOfficer;
	}
	public void setLegalOfficer(LegalOfficer legalOfficer) {
		this.legalOfficer = legalOfficer;
	}
    
}
