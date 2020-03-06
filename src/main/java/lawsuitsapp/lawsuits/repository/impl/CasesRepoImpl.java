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

import java.util.List;

@Repository
public class CasesRepoImpl implements CasesRepo {

    CasesRepoJPA casesRepoJPA;
    DocumentsRepo documentsRepo;

    public CasesRepoImpl(CasesRepoJPA casesRepoJPA, DocumentsRepo documentsRepo){
        this.casesRepoJPA = casesRepoJPA;
        this.documentsRepo = documentsRepo;
    }



    @Override
    public List<Case> getAllCases() {
        return casesRepoJPA.findAll();
    }

    @Override
    public Case getCaseById(int id) throws CaseNotFoundException {
        return casesRepoJPA.findById(id).orElseThrow(CaseNotFoundException::new);
    }




    // todo: FIX OVA - NAPRAVI GO ISTO KAKO BRISENJE NA EMPLOYEE
    @Override
    public void deleteCase(int id) {
        // get case
        Case caseToDelete = casesRepoJPA.getOne(id);

        // set all docs to null
        caseToDelete.getDocuments().stream().forEach(d -> {
            try {
                documentsRepo.setCaseIdToNull(d.getID());
            } catch (DocumentNotFoundException e) {
                e.printStackTrace();
            }
        });

        // delete case by id
        casesRepoJPA.deleteById(id);
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
