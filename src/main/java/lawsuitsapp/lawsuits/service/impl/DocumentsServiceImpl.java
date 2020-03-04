package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.repository.DocumentsRepo;
import lawsuitsapp.lawsuits.service.DocumentsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentsServiceImpl implements DocumentsService {

    DocumentsRepo documentsRepo;

    public DocumentsServiceImpl(DocumentsRepo documentsRepo) {
        this.documentsRepo = documentsRepo;
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentsRepo.getAllDocuments();
    }

    @Override
    public Document getDocumentById(int id) throws DocumentNotFoundException {
        return documentsRepo.getDocumentById(id);
    }

    @Override
    public void addDocument(Document document) {
        documentsRepo.addDocument(document);
    }


    @Override
    public void deleteDocument(int id) throws DocumentNotFoundException {
        documentsRepo.deleteDocument(id);
    }

    @Override
    public void editDocument(int oldId, Document newDocument) throws DocumentNotFoundException {
        documentsRepo.editDocument(oldId,newDocument);
    }

    @Override
    public List<Document> getAllDocumentsOfEmployeeById(int employeeId) {
        return documentsRepo.getAllDocumentsOfEmployeeById(employeeId);
    }
}
