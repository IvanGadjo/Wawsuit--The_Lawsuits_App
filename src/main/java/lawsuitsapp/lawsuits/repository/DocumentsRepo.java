package lawsuitsapp.lawsuits.repository;


import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface DocumentsRepo {

    List<Document> getAllDocuments();

    Document getDocumentById(int id) throws DocumentNotFoundException;

    void addDocument(Document document);

    void deleteDocument(int id) throws DocumentNotFoundException;

    void editDocument(int oldId, Document newDocument) throws DocumentNotFoundException;

    List<Document> getAllDocumentsOfEmployeeById(int employeeId) throws EmployeeNotFoundException;

    void setEmployeeIdToNull(int docId) throws DocumentNotFoundException;

    void setCaseIdToNull(int docId) throws DocumentNotFoundException;

    List<Document> getAllDocumentsOfCaseById(int caseId) throws CaseNotFoundException;
}
