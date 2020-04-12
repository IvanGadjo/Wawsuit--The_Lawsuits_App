package lawsuitsapp.lawsuits.repository.impl;

import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.repository.CasesRepo;
import lawsuitsapp.lawsuits.repository.DocumentsRepo;
import lawsuitsapp.lawsuits.repository.jpa.CasesRepoJPA;
import lawsuitsapp.lawsuits.repository.jpa.DocumentsRepoJPA;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void addCase(Case newCase) {
        casesRepoJPA.save(newCase);
    }

    @Override
    public void deleteCase(int id) throws CaseNotFoundException {
        Case caseToDelete = getCaseById(id);
        casesRepoJPA.delete(caseToDelete);


    }



    @Override
    public void changeParentCaseOfCase(int caseId, int parentCaseId) {

    }

    @Override
    public List<Case> searchCases(String term) {
        return casesRepoJPA.searchCases(term);
    }

}
