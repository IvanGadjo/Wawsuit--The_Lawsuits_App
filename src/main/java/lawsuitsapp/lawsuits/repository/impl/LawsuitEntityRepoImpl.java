package lawsuitsapp.lawsuits.repository.impl;

import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;
import lawsuitsapp.lawsuits.repository.LawsuitEntityRepo;
import lawsuitsapp.lawsuits.repository.jpa.LawsuitEntitiesJPA;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LawsuitEntityRepoImpl implements LawsuitEntityRepo {

    LawsuitEntitiesJPA lawsuitEntitiesJPA;

    public LawsuitEntityRepoImpl(LawsuitEntitiesJPA lawsuitEntitiesJPA){
        this.lawsuitEntitiesJPA = lawsuitEntitiesJPA;
    }


    @Override
    public List<LawsuitEntity> getAllLawsuitEntities() {
        return lawsuitEntitiesJPA.findAll();
    }

    @Override
    public LawsuitEntity getLawsuitEntityById(int id) throws LawsuitEntityNotFoundException {
        return lawsuitEntitiesJPA.findById(id).orElseThrow(LawsuitEntityNotFoundException::new);
    }

    @Override
    public void addLawsuitEntity(LawsuitEntity lawsuitEntity) {
        lawsuitEntitiesJPA.save(lawsuitEntity);
    }

    @Override
    public void deleteLawsuitEntity(int id) throws LawsuitEntityNotFoundException {
        LawsuitEntity lawsuitEntityToDelete = getLawsuitEntityById(id);
        lawsuitEntitiesJPA.delete(lawsuitEntityToDelete);
    }

    @Override
    public List<LawsuitEntity> searchLawsuitEntities(String term) {
        return lawsuitEntitiesJPA.searchLawsuitEntities(term);
    }
}
