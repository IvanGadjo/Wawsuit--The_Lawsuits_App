package lawsuitsapp.lawsuits.async;


import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.service.CasesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncCasesService {

    CasesService casesService;

    public AsyncCasesService(CasesService casesService){
        this.casesService = casesService;
    }


    public List<Case> getAllCasesAsync() {
        return casesService.getAllCases();
    }

    public Case getCaseByIdAsync(int id) throws CaseNotFoundException {
        return casesService.getCaseById(id);
    }

    public void deleteCaseAsync(int id) {
        casesService.deleteCase(id);
    }

    public void addCaseAsync(Case newCase) {

    }

    public void editCaseAsync(int oldId, Case editedCase) {

    }

    public void moveDocumentsBetweenCasesAsync(int idFrom, int idTo, List<Document> documents) {

    }

    public void changePhaseOfCaseAsync(int id, String newPhase) {

    }
}