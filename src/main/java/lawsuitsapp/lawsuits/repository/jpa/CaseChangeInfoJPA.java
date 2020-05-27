package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.historyLog.CaseChangeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaseChangeInfoJPA extends JpaRepository<CaseChangeInfo,Integer> {

    @Query(value = "select * from case_changes_info cci "+
            "where cci.case_id = :caseId", nativeQuery = true)
    List<CaseChangeInfo> getAllChangesOfCase(@Param("caseId") int caseId);
}
