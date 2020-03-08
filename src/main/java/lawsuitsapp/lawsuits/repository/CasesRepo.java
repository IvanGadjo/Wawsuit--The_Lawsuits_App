package lawsuitsapp.lawsuits.repository;

import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;

import java.util.List;

public interface CasesRepo {

    List<Case> getAllCases();

    Case getCaseById(int id) throws CaseNotFoundException;

    void addCase(Case newCase);

    void deleteCase(int id) throws CaseNotFoundException;

    void changeParentCaseOfCase(int caseId, int parentCaseId);

}
