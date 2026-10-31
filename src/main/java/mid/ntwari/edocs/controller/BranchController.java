package mid.ntwari.edocs.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mid.ntwari.edocs.model.Branch;
import mid.ntwari.edocs.service.BranchService;

@RestController
@RequestMapping("/branches")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Branch branch) {
        branchService.save(branch);
        return ResponseEntity.status(HttpStatus.CREATED).body("Branch saved successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Branch branch = branchService.findById(id);
        if (branch != null) {
            return ResponseEntity.ok(branch);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The branch with that is does not exist");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Branch branch) {

        Branch updatedBranch = branchService.update(id, branch);
        if (updatedBranch != null) {
            return ResponseEntity.ok(updatedBranch);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The branch with this id does not exist");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        boolean deleted = branchService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Branch deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The branch with that id does not exist");
        }
    }

    @GetMapping("/free")
    public ResponseEntity<List<Branch>> findFreeBranches() {
        return ResponseEntity.ok(branchService.findFreeBranches());
    }
}
