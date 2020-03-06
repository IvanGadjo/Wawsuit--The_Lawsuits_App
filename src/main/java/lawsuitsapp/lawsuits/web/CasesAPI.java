package lawsuitsapp.lawsuits.web;


import lawsuitsapp.lawsuits.async.AsyncCasesService;
import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import org.springframework.web.bind.annotation.*;

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
    public void deleteCaseFromRepo(@PathVariable("id") int id){
        asyncCasesService.deleteCaseAsync(id);
    }
}
