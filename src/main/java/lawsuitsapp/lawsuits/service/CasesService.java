package lawsuitsapp.lawsuits.service;

import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;

import java.util.List;

public interface CasesService {

    List<Case> getAllCases();

    Case getCaseById(int id) throws CaseNotFoundException;

    void addCase(Case newCase);

    void editCase(int oldId,Case editedCase);

    void moveDocumentsBetweenCases(int idFrom, int idTo, List<Document> documents);

    void changePhaseOfCase(int id, String newPhase);
}
