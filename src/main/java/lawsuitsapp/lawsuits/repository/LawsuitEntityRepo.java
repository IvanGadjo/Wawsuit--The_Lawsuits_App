package lawsuitsapp.lawsuits.repository;

import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;

import java.util.List;

public interface LawsuitEntityRepo {

    List<LawsuitEntity> getAllLawsuitEntities();

    LawsuitEntity getLawsuitEntityById(int id) throws LawsuitEntityNotFoundException;

    void addLawsuitEntity(LawsuitEntity lawsuitEntity);

    void deleteLawsuitEntity(int id) throws LawsuitEntityNotFoundException;
}
