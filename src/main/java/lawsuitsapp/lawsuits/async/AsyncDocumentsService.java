package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
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
}
