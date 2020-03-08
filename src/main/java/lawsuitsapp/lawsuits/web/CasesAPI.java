package lawsuitsapp.lawsuits.web;


import lawsuitsapp.lawsuits.async.AsyncCasesService;
import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/cases")
public class CasesAPI {

    AsyncCasesService asyncCasesService;

    public CasesAPI(AsyncCasesService asyncCasesService){
        this.asyncCasesService = asyncCasesService;
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
                              @RequestParam("parentCaseId") String parentCaseId) throws CaseNotFoundException {

        Case newCase = new Case(caseNumber,name,basis,value,phase,isExecuted);

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
                               @RequestParam("isExecuted")boolean isExecuted) throws CaseNotFoundException {
        Case editedCase = new Case(caseNumber,name,basis,value,phase,isExecuted);
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
