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


//
//        boolean isParent = true;
//
//        // get the case
//        Case caseToDelete = getCaseById(id);
//
//        // check if the case is a parent case
//        if (caseToDelete.getParentCase() != null){
//            isParent = false;
//        }
//
//                    // if it is CHILD
//        if(!isParent){
//
//        }
//        else {      // if it is PARENT
//
//            // set all docs of childs to null
//            List<Case> childCases = getAllCasesByParentCaseId(id);
//
//            childCases.stream().forEach(cc ->{
//                try {
//                    documentsRepo.getAllDocumentsOfCaseById(cc.getID()).stream().forEach(d ->{
//                        try {
//                            documentsRepo.setCaseIdToNull(d.getID());
//                        } catch (DocumentNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                    });
//                } catch (CaseNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            });
//
//            childCases = getAllCasesByParentCaseId(id); // re-read child cases (now they have no docs)
//
//            // delete child cases
//            casesRepoJPA.deleteAll(childCases);
//        }
//
//
//        // todo: od tuka dosredi
//
//        // set all docs to null     // todo: od tuka pocnuva novo
////        caseToDelete.getDocuments().stream().forEach(d -> {
////            try {
////                documentsRepo.setCaseIdToNull(d.getID());
////            } catch (DocumentNotFoundException e) {
////                e.printStackTrace();
////            }
////        });
//
//        documentsRepo.getAllDocumentsOfCaseById(id).stream().forEach(d ->{
//            try {
//                documentsRepo.setCaseIdToNull(d.getID());
//            } catch (DocumentNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//
//
//
//
//
//        // delete case by id
//        casesRepoJPA.deleteById(id);

    }






    // todo:


//    @Override
//    public void editCase(int oldId, Case editedCase) throws CaseNotFoundException {
//        // get old case
//        Case old = getCaseById(oldId);
//
//        // get docs
//        List<Document> documents = old.getDocuments();
//
//        // delete all docs
//        documents.stream().forEach(d -> documentsRepoJPA.delete(d));
//
//        // input the new case to the docs
//        documents.stream().forEach(d ->{
//            d.setCaseId(editedCase);
//        });
//
//        // fetch old case again
//        old = getCaseById(oldId);
//
//        // delete old case (this deletes child cases)
//        casesRepoJPA.delete(old);
//
//        // add parentcase id to edited case
//        editedCase.setParentCase(old.getParentCase());
//
//        // save new case
//        casesRepoJPA.save(editedCase);
//
//        // save docs
//        documentsRepoJPA.saveAll(documents);
//    }

//    @Override
//    public void moveDocumentsBetweenCases(int idTo, List<Integer> documentIds) throws CaseNotFoundException {
//        // get all docs
//        List<Document> documents = new ArrayList<>();
//        documentIds.stream().forEach(di -> {
//            try {
//                documents.add(documentsRepoJPA.findById(di).orElseThrow(DocumentNotFoundException::new));
//            } catch (DocumentNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
//
//        // delete all docs from repo
//        documentIds.stream().forEach(di -> documentsRepoJPA.deleteById(di));
//
//
//
//        // todo
//        // remove the docs from the old case
//
//
//
//
//        // get the case from repo
//        Case caseTo = casesRepoJPA.findById(idTo).orElseThrow(CaseNotFoundException::new);
//
//        // change caseId of docs to idTo
//        documents.stream().forEach(d -> d.setCaseId(caseTo));
//
//        // add the docs to the new case
//
//        // save the new case
//
//        // save all docs
//        documents.stream().forEach(d -> documentsRepoJPA.save(d));
//
//    }


    @Override
    public void changeParentCaseOfCase(int caseId, int parentCaseId) {

    }

    @Override
    public List<Case> searchCases(String term) {
        return casesRepoJPA.searchCases(term);
    }

}
