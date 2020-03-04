package lawsuitsapp.lawsuits.async;

import lawsuitsapp.lawsuits.model.Court;
import lawsuitsapp.lawsuits.model.exceptions.CourtNotFoundException;
import lawsuitsapp.lawsuits.service.CourtsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncCourtsService {

    CourtsService courtsService;

    public AsyncCourtsService(CourtsService courtsService){
        this.courtsService = courtsService;
    }

    public List<Court> getAllCourtsAsync() {
        return courtsService.getAllCourts();
    }

    public Court getCourtByIdAsync(int id) throws CourtNotFoundException {
        return courtsService.getCourtById(id);
    }

    public void addCourtAsync(Court newCourt) {
        courtsService.addCourt(newCourt);
    }

    public void editCourtAsync(int oldId, Court editCourt) throws CourtNotFoundException {
        courtsService.editCourt(oldId,editCourt);
    }

    public void deleteCourtAsync(int id) {
        courtsService.deleteCourt(id);
    }
}
