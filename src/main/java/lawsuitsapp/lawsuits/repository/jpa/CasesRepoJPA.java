package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CasesRepoJPA extends JpaRepository<Case, Integer>{

    @Query("select c from Case c " +
            "where c.name like %:term%")
    List<Case> searchCases(@Param("term") String term);
}
