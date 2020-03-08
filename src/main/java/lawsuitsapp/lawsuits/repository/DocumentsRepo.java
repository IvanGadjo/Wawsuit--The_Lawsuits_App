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

}
