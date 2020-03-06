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
    public void deleteCase(int id) {
        casesRepo.deleteCase(id);
    }



    // todo:

    @Override
    public void addCase(Case newCase) {

    }

    @Override
    public void editCase(int oldId, Case editedCase) {

    }

    @Override
    public void moveDocumentsBetweenCases(int idFrom, int idTo, List<Document> documents) {

    }

    @Override
    public void changePhaseOfCase(int id, String newPhase) {

    }

}
