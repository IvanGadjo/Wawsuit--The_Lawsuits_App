package lawsuitsapp.lawsuits.web;


import lawsuitsapp.lawsuits.async.AsyncCasesService;
import lawsuitsapp.lawsuits.async.AsyncEmployeeService;
import lawsuitsapp.lawsuits.async.AsyncLawsuitEntityService;
import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;
import lawsuitsapp.lawsuits.repository.jpa.LawsuitEntitiesJPA;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/cases")
public class CasesAPI {

    AsyncCasesService asyncCasesService;
    AsyncEmployeeService asyncEmployeeService;

    // todo: priveremeno samo
    LawsuitEntitiesJPA lawsuitEntitiesJPA;

    public CasesAPI(AsyncCasesService asyncCasesService, LawsuitEntitiesJPA lawsuitEntitiesJPA,
                    AsyncEmployeeService asyncEmployeeService){
        this.asyncCasesService = asyncCasesService;
        this.lawsuitEntitiesJPA = lawsuitEntitiesJPA;
        this.asyncEmployeeService = asyncEmployeeService;
    }

    @GetMapping
    public List<Case> getAllCasesFromRepo(){
        return asyncCasesService.getAllCasesAsync();
    }

    @GetMapping("/{id}")
    public Case getCaseByIdFromRepo(@PathVariable("id") int id) throws CaseNotFoundException {
        return asyncCasesService.getCaseByIdAsync(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCaseFromRepo(@PathVariable("id") int id) throws CaseNotFoundException {
        asyncCasesService.deleteCaseAsync(id);
    }

    @GetMapping("/childCases/{id}")
    public List<Case> getAllCasesByParentCaseIdFromRepo(@PathVariable("id") int id) throws CaseNotFoundException {
        return asyncCasesService.getAllCasesByParentCaseIdAsync(id);
    }




    // todo: mozebi ne mora sekoj pat da se znae koj e proxy pri vnesuvanje na nov case

    @PostMapping
    public Case addCaseToRepo(@RequestParam("caseNumber") int caseNumber,
                              @RequestParam("name") String name,
                              @RequestParam("basis") String basis,
                              @RequestParam("value") float value,
                              @RequestParam("phase") String phase,
                              @RequestParam("isExecuted")boolean isExecuted,
                              @RequestParam("parentCaseId") String parentCaseId,
                              @RequestParam("plaintiffId") int plaintiffId,
                              @RequestParam("suedId") int suedId,
                              @RequestParam("createdBy") int createdById,
                              @RequestParam("proxy") String proxy) throws CaseNotFoundException, LawsuitEntityNotFoundException, EmployeeNotFoundException {

        LawsuitEntity plaintiff = lawsuitEntitiesJPA.findById(plaintiffId).orElseThrow(LawsuitEntityNotFoundException::new);
        LawsuitEntity sued = lawsuitEntitiesJPA.findById(suedId).orElseThrow(LawsuitEntityNotFoundException::new);
        Employee creator = asyncEmployeeService.getEmployeeByIdAsync(createdById);


        Case newCase = new Case(caseNumber,name,basis,value,phase,isExecuted,plaintiff,sued,creator,proxy);

        // set the parent case
        if(!parentCaseId.equals("/")){
            newCase.setParentCase(asyncCasesService.getCaseByIdAsync(Integer.parseInt(parentCaseId)));
        }

        asyncCasesService.addCaseAsync(newCase);

        return newCase;
    }

    @PutMapping("/{id}")
    public Case editCaseInRepo(@PathVariable("id") int oldId,
                               @RequestParam("caseNumber") int caseNumber,
                               @RequestParam("name") String name,
                               @RequestParam("basis") String basis,
                               @RequestParam("value") float value,
                               @RequestParam("phase") String phase,
                               @RequestParam("isExecuted")boolean isExecuted,
                               @RequestParam("plaintiffId") int plaintiffId,
                               @RequestParam("suedId") int suedId,
                               @RequestParam("createdBy") int createdById,
                               @RequestParam("proxy") String proxy) throws CaseNotFoundException, LawsuitEntityNotFoundException, EmployeeNotFoundException {
        LawsuitEntity plaintiff = lawsuitEntitiesJPA.findById(plaintiffId).orElseThrow(LawsuitEntityNotFoundException::new);
        LawsuitEntity sued = lawsuitEntitiesJPA.findById(suedId).orElseThrow(LawsuitEntityNotFoundException::new);
        Employee creator = asyncEmployeeService.getEmployeeByIdAsync(createdById);

        Case editedCase = new Case(caseNumber,name,basis,value,phase,isExecuted,plaintiff,sued,creator,proxy);
        asyncCasesService.editCaseAsync(oldId,editedCase);

        return editedCase;
    }




    @GetMapping("/getPlaintiff/{id}")
    public LawsuitEntity getPlaintiffOfCase(@PathVariable("id") int id) throws CaseNotFoundException {
        return asyncCasesService.getCaseByIdAsync(id).getPlaintiff();
    }

    @GetMapping("/getSued/{id}")
    public LawsuitEntity getSuedInCase(@PathVariable("id") int id) throws CaseNotFoundException {
        return asyncCasesService.getCaseByIdAsync(id).getSued();
    }

    @PutMapping("/moveDocs")
    public void moveDocsBetweenCasesInRepo(@RequestParam("idTo") int idTo,
                                           @RequestParam("documentIds") List<Integer> documentIds) throws CaseNotFoundException {
        asyncCasesService.moveDocumentsBetweenCasesAsync(idTo,documentIds);
    }

    @PutMapping("/changePhase/{id}")
    public void changePhaseOfCaseInRepo(@PathVariable("id") int id,
                                        @RequestParam("newPhase") String newPhase) throws CaseNotFoundException {
        asyncCasesService.changePhaseOfCaseAsync(id,newPhase);
    }

    @PostMapping("/addEmployees/{id}")
    public void addEmployeesToCase(@PathVariable("id") int caseId,
                                   @RequestParam("employeeIds")List<Integer> employeeIds) throws CaseNotFoundException {
        asyncCasesService.addEmployeesToCaseAsync(caseId,employeeIds);
    }

    @PutMapping("/removeEmployees/{id}")
    public void removeEmployeesFromCase(@PathVariable("id") int caseId,
                                        @RequestParam("employeeIds")List<Integer> employeeIds) throws CaseNotFoundException{
        asyncCasesService.removeEmployeesFromCaseAsync(caseId,employeeIds);
    }

    // returns only case name and case number
    // important: do not change the method in the service as it is used by other services. If another functionality is
    // needed, make another method
    @GetMapping("/byEmployeeId/{id}")
    public List<String> getAllCasesOfEmployeeById(@PathVariable("id") int employeeId) throws EmployeeNotFoundException {
        return asyncCasesService.getCasesByEmployeeIdAsync(employeeId).stream()
                .map(c -> "Name: "+c.getName()+", Number: "+c.getCaseNumber()).collect(Collectors.toList());
    }

    // fixme: NOT USED
    @GetMapping("/caseEmployeeInfo")
    public List<String> getBasicCase_EmployeeInfo(){
        List<String> resultStrings = new ArrayList<>();

        List<Employee> employees = asyncEmployeeService.getAllEmployeesAsync();
        for (Employee e: employees){
            String rez;
            List<Case> cases = e.getCases();
            for (Case c: cases){
                rez = "Employee: "+e.getFirstName()+" "+e.getLastName()+
                        ", Case: "+c.getName()+" number: "+c.getCaseNumber();
                resultStrings.add(rez);
            }
        }

        return resultStrings;
    }


    @GetMapping("/search/{term}")
    public List<Case> searchCases(@PathVariable("term") String term){
        return asyncCasesService.searchCases(term);
    }
}
