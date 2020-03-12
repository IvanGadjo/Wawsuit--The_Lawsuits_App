package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.repository.CasesRepo;
import lawsuitsapp.lawsuits.repository.DocumentsRepo;
import lawsuitsapp.lawsuits.repository.EmployeeRepo;
import lawsuitsapp.lawsuits.service.DocumentsService;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentsServiceImpl implements DocumentsService {

    DocumentsRepo documentsRepo;
    EmployeeRepo employeeRepo;
    CasesRepo casesRepo;

    public DocumentsServiceImpl(DocumentsRepo documentsRepo, EmployeeRepo employeeRepo, CasesRepo casesRepo) {
        this.documentsRepo = documentsRepo;
        this.employeeRepo = employeeRepo;
        this.casesRepo = casesRepo;
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
        //documentsRepo.editDocument(oldId,newDocument);
        Document docToEdit = documentsRepo.getDocumentById(oldId);
        docToEdit.setName(newDocument.getName());
        docToEdit.setArchiveNumber(newDocument.getArchiveNumber());
        docToEdit.setInput(newDocument.isInput());
        docToEdit.setDocumentDate(newDocument.getDocumentDate());
        docToEdit.setFileType(newDocument.getFileType());
        docToEdit.setData(newDocument.getData());
        docToEdit.setCaseId(newDocument.getCaseId());
        docToEdit.setCreatedBy(newDocument.getCreatedBy());
        docToEdit.setCourt(newDocument.getCourt());

        documentsRepo.addDocument(docToEdit);
    }

    @Override
    public List<Document> getAllDocumentsOfEmployeeById(int employeeId) throws EmployeeNotFoundException {
        //return documentsRepo.getAllDocumentsOfEmployeeById(employeeId);
        Employee employee = employeeRepo.getEmployeeById(employeeId);
        return employee.getDocuments();
    }

    @Override
    public List<Document> getAllDocumentsOfCaseById(int caseId) throws CaseNotFoundException {
        Case theCase = casesRepo.getCaseById(caseId);
        return theCase.getDocuments();
    }

    @Override
    public void setEmployeeIdToNull(int docId) throws DocumentNotFoundException {
        //documentsRepo.setEmployeeIdToNull(docId);
        Document document = documentsRepo.getDocumentById(docId);
        document.setCreatedBy(null);
        documentsRepo.addDocument(document);
    }

    @Override
    public void setCaseIdToNull(int docId) throws DocumentNotFoundException {
        //documentsRepo.setCaseIdToNull(docId);
        Document document = documentsRepo.getDocumentById(docId);
        document.setCaseId(null);
        documentsRepo.addDocument(document);
    }
}
