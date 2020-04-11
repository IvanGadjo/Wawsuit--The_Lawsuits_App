package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.service.DocumentsService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncDocumentsService {

    DocumentsService documentsService;

    public AsyncDocumentsService(DocumentsService documentsService){
        this.documentsService = documentsService;
    }

    @Async
    public CompletableFuture<List<Document>> getAllDocumentsAsync() {
        return CompletableFuture.completedFuture(documentsService.getAllDocuments());
    }


    @Async
    public CompletableFuture<Document> getDocumentByIdAsync(int id) throws DocumentNotFoundException {
        return CompletableFuture.completedFuture(documentsService.getDocumentById(id));
    }


    @Async
    public void addDocumentAsync(Document document) {
        documentsService.addDocument(document);
    }

    @Async
    public void deleteDocumentAsync(int id) throws DocumentNotFoundException {
        documentsService.deleteDocument(id);
    }

    @Async
    public void editDocumentAsync(int oldId, Document newDocument) throws DocumentNotFoundException {
        documentsService.editDocument(oldId,newDocument);
    }

    @Async
    public CompletableFuture<List<Document>> getAllDocumentsOfEmployeeByIdAsync(int employeeId) throws EmployeeNotFoundException {
        return CompletableFuture.completedFuture(documentsService.getAllDocumentsOfEmployeeById(employeeId));
    }

    @Async
    public CompletableFuture<List<Document>> getAllDocumentsOfCaseByIdAsync(int caseId) throws CaseNotFoundException {
        return CompletableFuture.completedFuture(documentsService.getAllDocumentsOfCaseById(caseId));
    }

    @Async
    public void setEmployeeIdToNullAsync(int docId) throws DocumentNotFoundException {
        documentsService.setEmployeeIdToNull(docId);
    }

    @Async
    public void setCaseIdToNullAsync(int docId) throws DocumentNotFoundException {
        documentsService.setCaseIdToNull(docId);
    }
}
