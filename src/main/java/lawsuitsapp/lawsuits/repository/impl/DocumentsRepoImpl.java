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


    public DocumentsRepoImpl(DocumentsRepoJPA documentsRepoJPA, EmployeeRepoJPA employeeRepoJPA, CasesRepoJPA casesRepoJPA){
        this.documentsRepoJPA = documentsRepoJPA;
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






}
