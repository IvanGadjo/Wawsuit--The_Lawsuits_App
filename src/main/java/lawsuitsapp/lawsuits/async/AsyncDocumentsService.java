package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.service.DocumentsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncDocumentsService {

    DocumentsService documentsService;

    public AsyncDocumentsService(DocumentsService documentsService){
        this.documentsService = documentsService;
    }

    public List<Document> getAllDocumentsAsync() {
        return documentsService.getAllDocuments();
    }


    public Document getDocumentByIdAsync(int id) throws DocumentNotFoundException {
        return documentsService.getDocumentById(id);
    }


    public void addDocumentAsync(Document document) {
        documentsService.addDocument(document);
    }

    public void deleteDocumentAsync(int id) throws DocumentNotFoundException {
        documentsService.deleteDocument(id);
    }

    public void editDocumentAsync(int oldId, Document newDocument) throws DocumentNotFoundException {
        documentsService.editDocument(oldId,newDocument);
    }

    public List<Document> getAllDocumentsOfEmployeeByIdAsync(int employeeId) throws EmployeeNotFoundException {
        return documentsService.getAllDocumentsOfEmployeeById(employeeId);
    }

    public List<Document> getAllDocumentsOfCaseByIdAsync(int caseId) throws CaseNotFoundException {
        return documentsService.getAllDocumentsOfCaseById(caseId);
    }

    public void setEmployeeIdToNullAsync(int docId) throws DocumentNotFoundException {
        documentsService.setEmployeeIdToNull(docId);
    }

    public void setCaseIdToNullAsync(int docId) throws DocumentNotFoundException {
        documentsService.setCaseIdToNull(docId);
    }
}
