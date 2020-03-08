package lawsuitsapp.lawsuits.service.impl;


import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.repository.CasesRepo;
import lawsuitsapp.lawsuits.service.CasesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasesServiceImpl implements CasesService {

    CasesRepo casesRepo;

    public CasesServiceImpl(CasesRepo casesRepo){
        this.casesRepo = casesRepo;
    }


    @Override
    public List<Case> getAllCases() {
        return casesRepo.getAllCases();
    }

    @Override
    public Case getCaseById(int id) throws CaseNotFoundException {
        return casesRepo.getCaseById(id);
    }

    @Override
    public void deleteCase(int id) throws CaseNotFoundException {
        casesRepo.deleteCase(id);
    }






    // todo:

    @Override
    public void addCase(Case newCase) {
        casesRepo.addCase(newCase);
    }

    @Override
    public void editCase(int oldId, Case editedCase) throws CaseNotFoundException {
        casesRepo.editCase(oldId, editedCase);
    }

    @Override
    public void moveDocumentsBetweenCases(int idTo, List<Integer> documentIds) throws CaseNotFoundException {
        casesRepo.moveDocumentsBetweenCases(idTo,documentIds);
    }

    @Override
    public void changePhaseOfCase(int id, String newPhase) throws CaseNotFoundException {
        casesRepo.changePhaseOfCase(id,newPhase);
    }

    @Override
    public void changeParentCaseOfCase(int caseId, int parentCaseId) {

    }

    @Override
    public List<Case> getAllCasesByParentCaseId(int parentCaseId) throws CaseNotFoundException {
        return casesRepo.getAllCasesByParentCaseId(parentCaseId);
    }

}
