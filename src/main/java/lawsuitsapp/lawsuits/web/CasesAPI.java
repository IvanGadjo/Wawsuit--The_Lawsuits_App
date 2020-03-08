package lawsuitsapp.lawsuits.web;


import lawsuitsapp.lawsuits.async.AsyncCasesService;
import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;
import lawsuitsapp.lawsuits.repository.jpa.LawsuitEntitiesJPA;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/cases")
public class CasesAPI {

    AsyncCasesService asyncCasesService;

    // todo: priveremeno samo
    LawsuitEntitiesJPA lawsuitEntitiesJPA;

    public CasesAPI(AsyncCasesService asyncCasesService, LawsuitEntitiesJPA lawsuitEntitiesJPA){
        this.asyncCasesService = asyncCasesService;
        this.lawsuitEntitiesJPA = lawsuitEntitiesJPA;
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




    // todo:

    @PostMapping
    public void addCaseToRepo(@RequestParam("caseNumber") int caseNumber,
                              @RequestParam("name") String name,
                              @RequestParam("basis") String basis,
                              @RequestParam("value") float value,
                              @RequestParam("phase") String phase,
                              @RequestParam("isExecuted")boolean isExecuted,
                              @RequestParam("parentCaseId") String parentCaseId,
                              @RequestParam("plaintiffId") int plaintiffId,
                              @RequestParam("suedId") int suedId) throws CaseNotFoundException, LawsuitEntityNotFoundException {

        LawsuitEntity plaintiff = lawsuitEntitiesJPA.findById(plaintiffId).orElseThrow(LawsuitEntityNotFoundException::new);
        LawsuitEntity sued = lawsuitEntitiesJPA.findById(suedId).orElseThrow(LawsuitEntityNotFoundException::new);

        Case newCase = new Case(caseNumber,name,basis,value,phase,isExecuted,plaintiff,sued);

        // set the parent case
        if(!parentCaseId.equals("/")){
            newCase.setParentCase(asyncCasesService.getCaseByIdAsync(Integer.parseInt(parentCaseId)));
        }

        asyncCasesService.addCaseAsync(newCase);
    }

    @PutMapping("/{id}")
    public void editCaseInRepo(@PathVariable("id") int oldId,
                               @RequestParam("caseNumber") int caseNumber,
                               @RequestParam("name") String name,
                               @RequestParam("basis") String basis,
                               @RequestParam("value") float value,
                               @RequestParam("phase") String phase,
                               @RequestParam("isExecuted")boolean isExecuted,
                               @RequestParam("plaintiffId") int plaintiffId,
                               @RequestParam("suedId") int suedId) throws CaseNotFoundException, LawsuitEntityNotFoundException {
        LawsuitEntity plaintiff = lawsuitEntitiesJPA.findById(plaintiffId).orElseThrow(LawsuitEntityNotFoundException::new);
        LawsuitEntity sued = lawsuitEntitiesJPA.findById(suedId).orElseThrow(LawsuitEntityNotFoundException::new);

        Case editedCase = new Case(caseNumber,name,basis,value,phase,isExecuted,plaintiff,sued);
        asyncCasesService.editCaseAsync(oldId,editedCase);
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
}
