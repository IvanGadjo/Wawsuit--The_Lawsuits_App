package lawsuitsapp.lawsuits.web;

import lawsuitsapp.lawsuits.async.AsyncLawsuitEntityService;
import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/lawsuit-entities")
public class LawsuitEntitiesAPI {

    AsyncLawsuitEntityService asyncLawsuitEntityService;

    public LawsuitEntitiesAPI(AsyncLawsuitEntityService asyncLawsuitEntityService){
        this.asyncLawsuitEntityService = asyncLawsuitEntityService;
    }

    @GetMapping
    public List<LawsuitEntity> getAllLawsuitEntities() throws ExecutionException, InterruptedException {
        return asyncLawsuitEntityService.getAllLawsuitEntitiesAsync().get();
    }

    @GetMapping("/{id}")
    public LawsuitEntity getLawsuitEntityById(@PathVariable("id")int id) throws LawsuitEntityNotFoundException, ExecutionException, InterruptedException {
        return asyncLawsuitEntityService.getLawsuitEntityByIdAsync(id).get();
    }

    @PostMapping
    public LawsuitEntity addLawsuitEntity(@RequestParam("name") String name,
                                 @RequestParam("emb")int emb,
                                 @RequestParam("isCompany") boolean isCompany){
        LawsuitEntity lawsuitEntity = new LawsuitEntity(name,emb,isCompany);
        asyncLawsuitEntityService.addLawsuitEntityAsync(lawsuitEntity);
        return lawsuitEntity;
    }

    @PutMapping("/{oldId}")
    public LawsuitEntity editLawsuitEntity(@PathVariable("oldId") int oldId,
                                  @RequestParam("name") String name,
                                  @RequestParam("emb")int emb,
                                  @RequestParam("isCompany") boolean isCompany) throws LawsuitEntityNotFoundException {
        LawsuitEntity lawsuitEntity = new LawsuitEntity(name,emb,isCompany);
        asyncLawsuitEntityService.editLawsuitEntityAsync(oldId, lawsuitEntity);
        return lawsuitEntity;
    }

    @DeleteMapping("/{id}")
    public boolean deleteLawsuitEntity(@PathVariable("id") int id) throws LawsuitEntityNotFoundException, ExecutionException, InterruptedException {
        return asyncLawsuitEntityService.deleteLawsuitEntityAsync(id).get();
    }

    @GetMapping("/search/{term}")
    public List<LawsuitEntity> searchLawsuitEntities(@PathVariable("term") String term) throws ExecutionException, InterruptedException {
        return asyncLawsuitEntityService.searchLawsuitEntities(term).get();
    }
}
