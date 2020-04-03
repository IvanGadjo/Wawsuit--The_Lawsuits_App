package lawsuitsapp.lawsuits.service;

import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface CasesService {

    List<Case> getAllCases();

    Case getCaseById(int id) throws CaseNotFoundException;

    void addCase(Case newCase);

    void editCase(int oldId,Case editedCase) throws CaseNotFoundException;

    void moveDocumentsBetweenCases(int idTo, List<Integer> documentIds) throws CaseNotFoundException;

    void changePhaseOfCase(int id, String newPhase) throws CaseNotFoundException;

    void deleteCase(int id) throws CaseNotFoundException;

    void changeParentCaseOfCase(int caseId, int parentCaseId);

    List<Case> getAllCasesByParentCaseId(int parentCaseId) throws CaseNotFoundException;

    void setPlaintiffToNull(int caseId) throws CaseNotFoundException;

    void setSuedToNull(int caseId) throws CaseNotFoundException;

    void setEmployeeCreatorToNull(int caseId) throws CaseNotFoundException;

    void addEmployeesToCase(int caseId,List<Integer> employeeIds) throws CaseNotFoundException;

    void removeEmployeesFromCase(int caseId,List<Integer> employeeIds) throws CaseNotFoundException;

    List<Case> getCasesByEmployeeId(int employeeId) throws EmployeeNotFoundException;

    List<Case> searchCases(String term);
}
