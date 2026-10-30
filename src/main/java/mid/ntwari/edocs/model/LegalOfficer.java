package mid.ntwari.edocs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "legal_officer")
public class LegalOfficer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = true)
    private Branch branch;
    @ManyToMany
    @JoinTable(name = "legal_officer_document_type", joinColumns = @JoinColumn(name = "legal_officer_id"), inverseJoinColumns = @JoinColumn(name = "document_type_id"))
    private List<DocumentType> documentTypes = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void getEmail(String email) {
        this.email = email;
    }

    public Branch getBranch() {
        return branch;
    }
}
