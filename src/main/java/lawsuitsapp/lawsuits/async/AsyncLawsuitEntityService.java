package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;
import lawsuitsapp.lawsuits.service.LawsuitEntityService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class AsyncLawsuitEntityService {

    LawsuitEntityService lawsuitEntityService;

    public AsyncLawsuitEntityService(LawsuitEntityService lawsuitEntityService){
        this.lawsuitEntityService = lawsuitEntityService;
    }

    @Async
    public CompletableFuture<List<LawsuitEntity>> getAllLawsuitEntitiesAsync() {
        return CompletableFuture.completedFuture(lawsuitEntityService.getAllLawsuitEntities());
    }


    @Async
    public CompletableFuture<LawsuitEntity> getLawsuitEntityByIdAsync(int id) throws LawsuitEntityNotFoundException {
        return CompletableFuture.completedFuture(lawsuitEntityService.getLawsuitEntityById(id));
    }


    @Async
    public void addLawsuitEntityAsync(LawsuitEntity lawsuitEntity) {
        lawsuitEntityService.addLawsuitEntity(lawsuitEntity);
    }

    @Async
    public CompletableFuture<Boolean> deleteLawsuitEntityAsync(int id) throws LawsuitEntityNotFoundException {
        return CompletableFuture.completedFuture(lawsuitEntityService.deleteLawsuitEntity(id));
    }

    @Async
    public void editLawsuitEntityAsync(int oldId, LawsuitEntity newLawsuitEntity) throws LawsuitEntityNotFoundException {
        LawsuitEntity oldLE = lawsuitEntityService.getLawsuitEntityById(oldId);

        oldLE.setName(newLawsuitEntity.getName());
        oldLE.setEmb(newLawsuitEntity.getEmb());
        oldLE.setCompany(newLawsuitEntity.isCompany());

        lawsuitEntityService.addLawsuitEntity(oldLE);
    }

    @Async
    public CompletableFuture<List<LawsuitEntity>> searchLawsuitEntities(String term){
        return CompletableFuture.completedFuture(lawsuitEntityService.searchLawsuitEntities(term));
    }
}
