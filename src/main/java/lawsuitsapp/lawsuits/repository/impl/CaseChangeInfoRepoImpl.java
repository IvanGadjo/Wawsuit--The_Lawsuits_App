package lawsuitsapp.lawsuits.repository.impl;

import lawsuitsapp.lawsuits.model.exceptions.CaseChangeInfoNotFoundException;
import lawsuitsapp.lawsuits.model.historyLog.CaseChangeInfo;
import lawsuitsapp.lawsuits.repository.CaseChangeInfoRepo;
import lawsuitsapp.lawsuits.repository.jpa.CaseChangeInfoJPA;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CaseChangeInfoRepoImpl implements CaseChangeInfoRepo {

    CaseChangeInfoJPA caseChangeInfoJPA;

    public CaseChangeInfoRepoImpl(CaseChangeInfoJPA caseChangeInfoJPA){
        this.caseChangeInfoJPA = caseChangeInfoJPA;
    }

    @Override
    public List<CaseChangeInfo> getAllChanges() {
        return caseChangeInfoJPA.findAll();
    }

    @Override
    public CaseChangeInfo getChangeById(int id) throws CaseChangeInfoNotFoundException {
        return caseChangeInfoJPA.findById(id).orElseThrow(CaseChangeInfoNotFoundException::new);
    }

    @Override
    public void addChange(CaseChangeInfo change) {
        caseChangeInfoJPA.save(change);
    }

    @Override
    public void deleteChange(int id) {
        caseChangeInfoJPA.deleteById(id);
    }
}
