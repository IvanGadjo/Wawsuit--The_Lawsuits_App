package lawsuitsapp.lawsuits.repository;

import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.exceptions.CaseChangeInfoNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.historyLog.CaseChangeInfo;

import java.util.List;

public interface CaseChangeInfoRepo {

    List<CaseChangeInfo> getAllChanges();

    CaseChangeInfo getChangeById(int id) throws CaseChangeInfoNotFoundException;

    void addChange(CaseChangeInfo change);

    void deleteChange(int id);

    List<CaseChangeInfo> getAllChangesOfCase(int caseId);
}
