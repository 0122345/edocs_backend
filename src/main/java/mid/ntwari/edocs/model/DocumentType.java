package mid.ntwari.edocs.model;

import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "document_type")
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(mappedBy = "documentTypes")
    @JsonIgnore
    private List<LegalOfficer> legalOfficers;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LegalOfficer> getLegalOfficers() {
        return legalOfficers;
    }

    public void setLegalOfficers(List<LegalOfficer> legalOfficers) {
        this.legalOfficers = legalOfficers;
    }
}
