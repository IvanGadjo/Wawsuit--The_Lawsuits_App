package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.model.Court;
import lawsuitsapp.lawsuits.model.exceptions.CourtNotFoundException;
import lawsuitsapp.lawsuits.service.CourtsService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsyncCourtsService {

    CourtsService courtsService;

    public AsyncCourtsService(CourtsService courtsService){
        this.courtsService = courtsService;
    }


    @Async
    public CompletableFuture<List<Court>> getAllCourtsAsync(){
        return CompletableFuture.completedFuture(courtsService.getAllCourts());
    }

//    public Court getCourtByIdAsync(int id) throws CourtNotFoundException {
//        return courtsService.getCourtById(id);
//    }
    @Async
    public CompletableFuture<Court> getCourtByIdAsync(int id) throws CourtNotFoundException, InterruptedException {
        System.out.println("~~~~~~~~~~~~~~in thread: "+Thread.currentThread().getName());
        //Thread.sleep(2000L);
        return CompletableFuture.completedFuture(courtsService.getCourtById(id));
    }

    @Async
    public void addCourtAsync(Court newCourt) {
        courtsService.addCourt(newCourt);
    }

    @Async
    public void editCourtAsync(int oldId, Court editCourt) throws CourtNotFoundException {
        courtsService.editCourt(oldId,editCourt);
    }

    public void deleteCourtAsync(int id) {
        courtsService.deleteCourt(id);
    }
}
