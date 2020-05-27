package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.exceptions.CaseChangeInfoNotFoundException;
import lawsuitsapp.lawsuits.model.historyLog.CaseChangeInfo;
import lawsuitsapp.lawsuits.repository.CaseChangeInfoRepo;
import lawsuitsapp.lawsuits.service.CaseChangeInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseChangeInfoServiceImpl implements CaseChangeInfoService {

    CaseChangeInfoRepo caseChangeInfoRepo;

    public CaseChangeInfoServiceImpl(CaseChangeInfoRepo caseChangeInfoRepo){
        this.caseChangeInfoRepo = caseChangeInfoRepo;
    }

    @Override
    public List<CaseChangeInfo> getAllChanges() {
        return caseChangeInfoRepo.getAllChanges();
    }

    @Override
    public CaseChangeInfo getChangeById(int id) throws CaseChangeInfoNotFoundException {
        return caseChangeInfoRepo.getChangeById(id);
    }

    @Override
    public void addChange(CaseChangeInfo change) {
        caseChangeInfoRepo.addChange(change);
    }

    @Override
    public void deleteChange(int id) {
        caseChangeInfoRepo.deleteChange(id);
    }

    @Override
    public void setCaseIdToNull(int caseId) {
        List<CaseChangeInfo> listCaseChanges = caseChangeInfoRepo.getAllChangesOfCase(caseId);
        listCaseChanges.stream().forEach(cci -> {
            cci.setTheCase(null);
            caseChangeInfoRepo.addChange(cci);
        });
    }
}
