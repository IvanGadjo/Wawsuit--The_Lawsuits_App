package lawsuitsapp.lawsuits.service;

import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;

import java.util.List;

public interface LawsuitEntityService {

    List<LawsuitEntity> getAllLawsuitEntities();

    LawsuitEntity getLawsuitEntityById(int id) throws LawsuitEntityNotFoundException;

    void addLawsuitEntity(LawsuitEntity lawsuitEntity);

    boolean deleteLawsuitEntity(int id) throws LawsuitEntityNotFoundException;

    void editLawsuitEntity(int oldId, LawsuitEntity newLawsuitEntity) throws LawsuitEntityNotFoundException;

    List<LawsuitEntity> searchLawsuitEntities(String term);
}
