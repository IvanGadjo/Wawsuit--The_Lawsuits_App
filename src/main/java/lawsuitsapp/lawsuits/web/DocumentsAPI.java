package lawsuitsapp.lawsuits.web;


import lawsuitsapp.lawsuits.async.AsyncCasesService;
import lawsuitsapp.lawsuits.async.AsyncCourtsService;
import lawsuitsapp.lawsuits.async.AsyncDocumentsService;
import lawsuitsapp.lawsuits.async.AsyncEmployeeService;
import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Court;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.CourtNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/documents")
public class DocumentsAPI {

    AsyncDocumentsService asyncDocumentsService;
    AsyncEmployeeService asyncEmployeeService;
    AsyncCourtsService asyncCourtsService;
    AsyncCasesService asyncCasesService;

    public DocumentsAPI(AsyncDocumentsService asyncDocumentsService,AsyncEmployeeService asyncEmployeeService,
                        AsyncCourtsService asyncCourtsService, AsyncCasesService asyncCasesService){
        this.asyncDocumentsService = asyncDocumentsService;
        this.asyncEmployeeService = asyncEmployeeService;
        this.asyncCourtsService = asyncCourtsService;
        this.asyncCasesService = asyncCasesService;
    }

    @GetMapping
    public List<Document> getAllDocumentsFromRepo(){
        return asyncDocumentsService.getAllDocumentsAsync();
    }

    @GetMapping("/{id}")
    public Document getDocumentByIdFromRepo(@PathVariable("id") int id) throws DocumentNotFoundException {
        return asyncDocumentsService.getDocumentByIdAsync(id);
    }

    // todo: Ova sig ke treba da go smenis, da go napravis kako fileUpload vo telephones app
    @PostMapping
    public void addDocument(@RequestParam("name") String name,
                            @RequestParam("archiveNumber") int archiveNumber,
                            @RequestParam("isInput") boolean isInput,
                            @RequestParam("documentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate documentDate,
                            @RequestParam("fileType") String fileType,
                            @RequestParam("data") byte[] data,
                            @RequestParam("employeeId") int employeeId,
                            @RequestParam("courtId") int courtId,
                            @RequestParam("caseId") int caseId) throws EmployeeNotFoundException, CourtNotFoundException, CaseNotFoundException {

        Employee employee = asyncEmployeeService.getEmployeeByIdAsync(employeeId);
        Court court = asyncCourtsService.getCourtByIdAsync(courtId);
        Case docCase = asyncCasesService.getCaseByIdAsync(caseId);

        Document newDocument = new Document(name,archiveNumber,isInput,documentDate,fileType,data,employee,court,docCase);
        asyncDocumentsService.addDocumentAsync(newDocument);
    }



    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable("id") int id) throws DocumentNotFoundException {
        asyncDocumentsService.deleteDocumentAsync(id);
    }

    // ne moze edit na content, samo na info za documentot
    @PutMapping("/{oldId}")
    public void editDocument(@PathVariable("oldId") int oldId,
                             @RequestParam("name") String name,
                             @RequestParam("archiveNumber") int archiveNumber,
                             @RequestParam("isInput") boolean isInput,
                             @RequestParam("documentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate documentDate,
                             @RequestParam("employeeId") int employeeId,
                             @RequestParam("courtId") int courtId,
                             @RequestParam("caseId") int caseId) throws DocumentNotFoundException, EmployeeNotFoundException, CourtNotFoundException, CaseNotFoundException {

        Document oldDoc = asyncDocumentsService.getDocumentByIdAsync(oldId);
        Employee employee = asyncEmployeeService.getEmployeeByIdAsync(employeeId);
        Court court = asyncCourtsService.getCourtByIdAsync(courtId);
        Case docCase = asyncCasesService.getCaseByIdAsync(caseId);

        Document editDoc = new Document(name,archiveNumber,isInput,documentDate,oldDoc.getFileType(),oldDoc.getData(),
                employee,court,docCase);
        asyncDocumentsService.editDocumentAsync(oldId,editDoc);
    }

    @PutMapping("/removeCreator/{id}")
    public void setEmployeeIdToNull(@PathVariable("id") int id) throws DocumentNotFoundException {
        asyncDocumentsService.setEmployeeIdToNullAsync(id);
    }

    @PutMapping("/removeCase/{id}")
    public void setCaseIdToNull(@PathVariable("id") int id) throws DocumentNotFoundException {
        asyncDocumentsService.setCaseIdToNullAsync(id);
    }

    @GetMapping("/ofEmployee/{id}")
    public List<Document> getAllDocumentsOfEmployee(@PathVariable("id")int employeeId){
        return asyncDocumentsService.getAllDocumentsOfEmployeeByIdAsync(employeeId);
    }
}
