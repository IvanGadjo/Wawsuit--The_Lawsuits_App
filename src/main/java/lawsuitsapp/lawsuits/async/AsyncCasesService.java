package lawsuitsapp.lawsuits.async;


import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.service.CasesService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncCasesService {

    CasesService casesService;

    public AsyncCasesService(CasesService casesService){
        this.casesService = casesService;
    }


    @Async
    public CompletableFuture<List<Case>> getAllCasesAsync() {
        return CompletableFuture.completedFuture(casesService.getAllCases());
    }

    @Async
    public CompletableFuture<Case> getCaseByIdAsync(int id) throws CaseNotFoundException {
        return CompletableFuture.completedFuture(casesService.getCaseById(id));
    }

    @Async
    public void deleteCaseAsync(int id) throws CaseNotFoundException {
        casesService.deleteCase(id);
    }

    @Async
    public void addCaseAsync(Case newCase) {
        casesService.addCase(newCase);
    }

    @Async
    public void editCaseAsync(int oldId, Case editedCase) throws CaseNotFoundException {
        casesService.editCase(oldId,editedCase);
    }

    @Async
    public void moveDocumentsBetweenCasesAsync(int idTo, List<Integer> documentIds) throws CaseNotFoundException {
        casesService.moveDocumentsBetweenCases(idTo,documentIds);
    }

    @Async
    public void changePhaseOfCaseAsync(int id, String newPhase) throws CaseNotFoundException {
        casesService.changePhaseOfCase(id,newPhase);
    }

    @Async
    public CompletableFuture<List<Case>> getAllCasesByParentCaseIdAsync(int parentCaseId) throws CaseNotFoundException {
        return CompletableFuture.completedFuture(casesService.getAllCasesByParentCaseId(parentCaseId));
    }

    @Async
    public void addEmployeesToCaseAsync(int caseId,List<Integer> employees) throws CaseNotFoundException {
        casesService.addEmployeesToCase(caseId,employees);
    }

    @Async
    public void removeEmployeesFromCaseAsync(int caseId,List<Integer> employees) throws CaseNotFoundException {
        casesService.removeEmployeesFromCase(caseId,employees);
    }

    @Async
    public CompletableFuture<List<Case>> getCasesByEmployeeIdAsync(int employeeId) throws EmployeeNotFoundException {
        return CompletableFuture.completedFuture(casesService.getCasesByEmployeeId(employeeId));
    }

    @Async
    public CompletableFuture<List<Case>> searchCases(String term){
        return CompletableFuture.completedFuture(casesService.searchCases(term));
    }
}
