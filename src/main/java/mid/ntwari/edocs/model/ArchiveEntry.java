package mid.ntwari.edocs.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="archive_entry")
public class ArchiveEntry {
    @Id
    @GeneratedValue 
    private UUID id;

    private String archiveTag;
    private String remarks;
    private int retentionYears;
    private LocalDate  archiveDate;

    @ManyToOne
    @JoinColumn(name = "signature_request_id")
    private SignatureRequest signatureRequest;

    public ArchiveEntry(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getArchiveTag() {
        return archiveTag;
    }

    public void setArchiveTag(String archiveTag) {
        this.archiveTag = archiveTag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getRetentionYears() {
        return retentionYears;
    }

    public void setRetentionYears(int retentionYears) {
        this.retentionYears = retentionYears;
    }

    public LocalDate getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(LocalDate archiveDate) {
        this.archiveDate = archiveDate;
    }

    public SignatureRequest getSignatureRequest() {
        return signatureRequest;
    }

    public void setSignatureRequest(SignatureRequest signatureRequest) {
        this.signatureRequest = signatureRequest;
    }
}
