package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasesRepoJPA extends JpaRepository<Case, Integer>{
}
