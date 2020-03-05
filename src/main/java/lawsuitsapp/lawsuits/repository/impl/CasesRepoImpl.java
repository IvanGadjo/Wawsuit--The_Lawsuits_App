package lawsuitsapp.lawsuits.repository.impl;

import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.repository.CasesRepo;
import lawsuitsapp.lawsuits.repository.jpa.CasesRepoJPA;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CasesRepoImpl implements CasesRepo {

    CasesRepoJPA casesRepoJPA;

    public CasesRepoImpl(CasesRepoJPA casesRepoJPA){
        this.casesRepoJPA = casesRepoJPA;
    }



    @Override
    public List<Case> getAllCases() {
        return casesRepoJPA.findAll();
    }

    @Override
    public Case getCaseById(int id) throws CaseNotFoundException {
        return casesRepoJPA.findById(id).orElseThrow(CaseNotFoundException::new);
    }

    // todo:

    @Override
    public void addCase(Case newCase) {
        casesRepoJPA.save(newCase);
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
