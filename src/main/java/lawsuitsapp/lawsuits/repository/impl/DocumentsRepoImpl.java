package lawsuitsapp.lawsuits.repository.impl;


import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.repository.DocumentsRepo;
import lawsuitsapp.lawsuits.repository.jpa.CasesRepoJPA;
import lawsuitsapp.lawsuits.repository.jpa.DocumentsRepoJPA;
import lawsuitsapp.lawsuits.repository.jpa.EmployeeRepoJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DocumentsRepoImpl implements DocumentsRepo {

    DocumentsRepoJPA documentsRepoJPA;
    EmployeeRepoJPA employeeRepoJPA;
    CasesRepoJPA casesRepoJPA;

    public DocumentsRepoImpl(DocumentsRepoJPA documentsRepoJPA, EmployeeRepoJPA employeeRepoJPA, CasesRepoJPA casesRepoJPA){
        this.documentsRepoJPA = documentsRepoJPA;
        this.employeeRepoJPA = employeeRepoJPA;
        this.casesRepoJPA = casesRepoJPA;
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentsRepoJPA.findAll();
    }

    @Override
    public Document getDocumentById(int id) throws DocumentNotFoundException {
        return documentsRepoJPA.findById(id).orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public void addDocument(Document document) {
        documentsRepoJPA.save(document);
    }


    @Override
    public void deleteDocument(int id) throws DocumentNotFoundException {
        Document docToDelete = getDocumentById(id);
        documentsRepoJPA.delete(docToDelete);
    }



    // samo edit na infoto za document-ot a ne i contentot
    @Override
    public void editDocument(int oldId, Document newDocument) throws DocumentNotFoundException {
        Document oldDoc = getDocumentById(oldId);
        documentsRepoJPA.delete(oldDoc);
        documentsRepoJPA.save(newDocument);
    }

    @Override
    public List<Document> getAllDocumentsOfEmployeeById(int employeeId) throws EmployeeNotFoundException {
//        return documentsRepoJPA.findAll().stream().filter(d -> d.getCreatedBy().getID() == employeeId)
//                .collect(Collectors.toList());
        Employee employee = employeeRepoJPA.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
        return employee.getDocuments();
    }

    @Override
    public List<Document> getAllDocumentsOfCaseById(int caseId) throws CaseNotFoundException {
        Case theCase = casesRepoJPA.findById(caseId).orElseThrow(CaseNotFoundException::new);
        return theCase.getDocuments();
    }

    @Override
    public void setEmployeeIdToNull(int docId) throws DocumentNotFoundException {
        // get the doc
        Document oldDoc = getDocumentById(docId);

        // delete the doc from repo
        documentsRepoJPA.deleteById(docId);

        // change the doc (add null to createdBy)
        oldDoc.setCreatedBy(null);

        // save edited doc in repo
        documentsRepoJPA.save(oldDoc);
    }

    @Override
    public void setCaseIdToNull(int docId) throws DocumentNotFoundException {
        // get the doc
        Document oldDoc = getDocumentById(docId);

        // delete the doc from repo
        documentsRepoJPA.deleteById(docId);

        // change the doc (add null to caseId)
        oldDoc.setCaseId(null);

        // save edited doc in repo
        documentsRepoJPA.save(oldDoc);
    }
}
