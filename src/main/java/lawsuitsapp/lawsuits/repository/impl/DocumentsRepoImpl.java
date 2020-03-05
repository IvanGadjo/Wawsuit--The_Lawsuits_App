package lawsuitsapp.lawsuits.repository.impl;


import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.repository.DocumentsRepo;
import lawsuitsapp.lawsuits.repository.jpa.DocumentsRepoJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DocumentsRepoImpl implements DocumentsRepo {

    DocumentsRepoJPA documentsRepoJPA;

    public DocumentsRepoImpl(DocumentsRepoJPA documentsRepoJPA){
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

    // samo edit na infoto za document-ot a ne i contentot
    @Override
    public void editDocument(int oldId, Document newDocument) throws DocumentNotFoundException {
        Document oldDoc = getDocumentById(oldId);
        documentsRepoJPA.delete(oldDoc);
        documentsRepoJPA.save(newDocument);
    }

    @Override
    public List<Document> getAllDocumentsOfEmployeeById(int employeeId) {
        return documentsRepoJPA.findAll().stream().filter(d -> d.getCreatedBy().getID() == employeeId)
                .collect(Collectors.toList());
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
