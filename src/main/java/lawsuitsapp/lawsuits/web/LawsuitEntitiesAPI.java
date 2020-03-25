package lawsuitsapp.lawsuits.web;

import lawsuitsapp.lawsuits.async.AsyncLawsuitEntityService;
import lawsuitsapp.lawsuits.model.LawsuitEntity;
import lawsuitsapp.lawsuits.model.exceptions.LawsuitEntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/lawsuit-entities")
public class LawsuitEntitiesAPI {

    AsyncLawsuitEntityService asyncLawsuitEntityService;

    public LawsuitEntitiesAPI(AsyncLawsuitEntityService asyncLawsuitEntityService){
        this.asyncLawsuitEntityService = asyncLawsuitEntityService;
    }

    @GetMapping
    public List<LawsuitEntity> getAllLawsuitEntities(){
        return asyncLawsuitEntityService.getAllLawsuitEntitiesAsync();
    }

    @GetMapping("/{id}")
    public LawsuitEntity getLawsuitEntityById(@PathVariable("id")int id) throws LawsuitEntityNotFoundException {
        return asyncLawsuitEntityService.getLawsuitEntityByIdAsync(id);
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
    public void editLawsuitEntity(@PathVariable("oldId") int oldId,
                                  @RequestParam("name") String name,
                                  @RequestParam("emb")int emb,
                                  @RequestParam("isCompany") boolean isCompany) throws LawsuitEntityNotFoundException {
        LawsuitEntity lawsuitEntity = new LawsuitEntity(name,emb,isCompany);
        asyncLawsuitEntityService.editLawsuitEntityAsync(oldId, lawsuitEntity);
    }
}
