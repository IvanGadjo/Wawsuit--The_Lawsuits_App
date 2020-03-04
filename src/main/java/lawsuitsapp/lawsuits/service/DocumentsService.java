package lawsuitsapp.lawsuits.service;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;

import java.util.List;

public interface DocumentsService {

    List<Document> getAllDocuments();

    Document getDocumentById(int id) throws DocumentNotFoundException;

    void addDocument(Document document);

    void deleteDocument(int id) throws DocumentNotFoundException;

    void editDocument(int oldId, Document newDocument) throws DocumentNotFoundException;
}
