package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.historyLog.CaseChangeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseChangeInfoJPA extends JpaRepository<CaseChangeInfo,Integer> {
}
