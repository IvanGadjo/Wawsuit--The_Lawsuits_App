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
import lawsuitsapp.lawsuits.model.responses.Document_Response_Info;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
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
    public List<Document_Response_Info> getAllDocumentsInfo(){

        return asyncDocumentsService.getAllDocumentsAsync().stream().map(d ->{
            return new Document_Response_Info(d.getID(),d.getName(),d.getArchiveNumber(),d.isInput(),d.getDocumentDate(),
                    d.getFileType(),d.getCreatedBy().getFirstName()+" "+d.getCreatedBy().getLastName());
        }).collect(Collectors.toList());
    }


    @GetMapping("/downloadDocument/{id}")
    public ResponseEntity<byte[]> downloadDocumentById(@PathVariable("id") int id) throws DocumentNotFoundException {
        Document document = asyncDocumentsService.getDocumentByIdAsync(id);

//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(document.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
//                //.header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,"*")
//                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"GET,POST,PUT,DELETE,OPTIONS")
//                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"Authorization")
//                .body(new ByteArrayResource(document.getData()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(document.getFileType()));
        headers.setContentDisposition(ContentDisposition.parse("attachment; filename=\"" + document.getName() + "\""));
        headers.setAccessControlAllowMethods(Collections.singletonList(HttpMethod.GET));
        headers.setAccessControlAllowHeaders(Collections.singletonList("Authorization"));

        // todo: Mozebi treba body tuka da bide ByteArrayRessource a ne samo byte[]
        return new ResponseEntity<>(document.getData(),headers,HttpStatus.OK);
    }


    @PostMapping(value = "/uploadDocument", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadDocument(@RequestParam("archiveNumber") int archiveNumber,
                            @RequestParam("isInput") boolean isInput,
                            @RequestParam("documentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate documentDate,
                            @RequestParam("file")MultipartFile file,
                            @RequestParam("employeeId") int employeeId,
                            @RequestParam("courtId") int courtId,
                            @RequestParam("caseId") int caseId) throws EmployeeNotFoundException, CourtNotFoundException, CaseNotFoundException, IOException {

        Employee employee = asyncEmployeeService.getEmployeeByIdAsync(employeeId);
        Court court = asyncCourtsService.getCourtByIdAsync(courtId);
        Case docCase = asyncCasesService.getCaseByIdAsync(caseId);

        // normalize the filename
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Document newDocument = new Document(fileName,archiveNumber,isInput,documentDate,
                file.getContentType(),file.getBytes(),employee,court,docCase);
        asyncDocumentsService.addDocumentAsync(newDocument);
    }


    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable("id") int id) throws DocumentNotFoundException {
        asyncDocumentsService.deleteDocumentAsync(id);
    }

    // fixme: pri edit createdBy se menuva na editorot, isto kako i edit case
    // ne moze edit na content, samo na info za documentot
    @PutMapping("/{oldId}")
    public void editDocument(@PathVariable("oldId") int oldId,
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

        Document editDoc = new Document(oldDoc.getName(),archiveNumber,isInput,documentDate,oldDoc.getFileType(),oldDoc.getData(),
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

    // fixme - spored reactot ke vidis dali treba da vrakja names,ids ili i dvete
    @GetMapping("/ofEmployee/{id}")
    public List<Document> getAllDocumentsOfEmployee(@PathVariable("id")int employeeId) throws EmployeeNotFoundException {
        return asyncDocumentsService.getAllDocumentsOfEmployeeByIdAsync(employeeId);
    }

    // fixme - spored reactot ke vidis dali treba da vrakja names,ids ili i dvete
    @GetMapping("/ofCase/{id}")
    public List<Document_Response_Info> getAllDocumentsOfCase(@PathVariable("id") int caseId) throws CaseNotFoundException {
        return asyncDocumentsService.getAllDocumentsOfCaseByIdAsync(caseId).stream().map(d ->{
            return new Document_Response_Info(d.getID(),d.getName(),d.getArchiveNumber(),d.isInput(),d.getDocumentDate(),
                    d.getFileType(),d.getCreatedBy().getFirstName()+" "+d.getCreatedBy().getLastName());
        }).collect(Collectors.toList());
    }
}
