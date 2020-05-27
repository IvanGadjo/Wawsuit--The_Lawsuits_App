package lawsuitsapp.lawsuits.service;

import lawsuitsapp.lawsuits.model.exceptions.CaseChangeInfoNotFoundException;
import lawsuitsapp.lawsuits.model.historyLog.CaseChangeInfo;

import java.util.List;

public interface CaseChangeInfoService {

    List<CaseChangeInfo> getAllChanges();

    CaseChangeInfo getChangeById(int id) throws CaseChangeInfoNotFoundException;

    void addChange(CaseChangeInfo change);

    void deleteChange(int id);

    void setCaseIdToNull(int caseId);
}
