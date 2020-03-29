package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;
import lawsuitsapp.lawsuits.service.LawsuitEntityService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AsyncLawsuitEntityService {

    LawsuitEntityService lawsuitEntityService;

    public AsyncLawsuitEntityService(LawsuitEntityService lawsuitEntityService){
        this.lawsuitEntityService = lawsuitEntityService;
    }

    public List<LawsuitEntity> getAllLawsuitEntitiesAsync() {
        return lawsuitEntityService.getAllLawsuitEntities();
    }


    public LawsuitEntity getLawsuitEntityByIdAsync(int id) throws LawsuitEntityNotFoundException {
        return lawsuitEntityService.getLawsuitEntityById(id);
    }


    public void addLawsuitEntityAsync(LawsuitEntity lawsuitEntity) {
        lawsuitEntityService.addLawsuitEntity(lawsuitEntity);
    }


    public boolean deleteLawsuitEntityAsync(int id) throws LawsuitEntityNotFoundException {
        return lawsuitEntityService.deleteLawsuitEntity(id);
    }


    public void editLawsuitEntityAsync(int oldId, LawsuitEntity newLawsuitEntity) throws LawsuitEntityNotFoundException {
        LawsuitEntity oldLE = lawsuitEntityService.getLawsuitEntityById(oldId);

        oldLE.setName(newLawsuitEntity.getName());
        oldLE.setEmb(newLawsuitEntity.getEmb());
        oldLE.setCompany(newLawsuitEntity.isCompany());

        lawsuitEntityService.addLawsuitEntity(oldLE);
    }
}
