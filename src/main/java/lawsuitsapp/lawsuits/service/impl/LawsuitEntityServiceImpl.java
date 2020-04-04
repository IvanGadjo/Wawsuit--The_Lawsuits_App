package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;
import lawsuitsapp.lawsuits.repository.LawsuitEntityRepo;
import lawsuitsapp.lawsuits.service.CasesService;
import lawsuitsapp.lawsuits.service.LawsuitEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LawsuitEntityServiceImpl implements LawsuitEntityService {

    LawsuitEntityRepo lawsuitEntityRepo;
    CasesService casesService;

    public LawsuitEntityServiceImpl(LawsuitEntityRepo lawsuitEntityRepo, CasesService casesService){
        this.lawsuitEntityRepo = lawsuitEntityRepo;
        this.casesService = casesService;
    }



    @Override
    public List<LawsuitEntity> getAllLawsuitEntities() {
        return lawsuitEntityRepo.getAllLawsuitEntities();
    }

    @Override
    public LawsuitEntity getLawsuitEntityById(int id) throws LawsuitEntityNotFoundException {
        return lawsuitEntityRepo.getLawsuitEntityById(id);
    }

    @Override
    public void addLawsuitEntity(LawsuitEntity lawsuitEntity) {
        lawsuitEntityRepo.addLawsuitEntity(lawsuitEntity);
    }



    // fixme: nigde ne se koristi metodov, valjda ne treba da se stava null na ovie vo case-ot sto im odgovara
    // fixme: ne smee da ima case bez plaintiff i sued
    @Override
    public boolean deleteLawsuitEntity(int id) throws LawsuitEntityNotFoundException {

        LawsuitEntity lawsuitEntity = lawsuitEntityRepo.getLawsuitEntityById(id);

        if(lawsuitEntity.getCasesSued().size() == 0 && lawsuitEntity.getCasesPlaintiff().size() == 0){
            lawsuitEntityRepo.deleteLawsuitEntity(id);
            return true;
        }

        return false;
    }

    // fixme: Ne se koristi nigde, treba da se koristi vo async, a logikata od tamu da se premesti tuka
    @Override
    public void editLawsuitEntity(int oldId, LawsuitEntity newLawsuitEntity) throws LawsuitEntityNotFoundException {
        LawsuitEntity oldLE = lawsuitEntityRepo.getLawsuitEntityById(oldId);

        oldLE.setName(newLawsuitEntity.getName());
        oldLE.setEmb(newLawsuitEntity.getEmb());
        oldLE.setCompany(newLawsuitEntity.isCompany());

        lawsuitEntityRepo.addLawsuitEntity(oldLE);
    }

    @Override
    public List<LawsuitEntity> searchLawsuitEntities(String term) {
        return lawsuitEntityRepo.searchLawsuitEntities(term);
    }
}
