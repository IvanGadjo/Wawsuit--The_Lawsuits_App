package lawsuitsapp.lawsuits.service.impl;

import lawsuitsapp.lawsuits.model.Court;
import lawsuitsapp.lawsuits.model.exceptions.CourtNotFoundException;
import lawsuitsapp.lawsuits.repository.CourtsRepo;
import lawsuitsapp.lawsuits.service.CourtsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtsServiceImpl implements CourtsService {

    CourtsRepo courtsRepo;

    public CourtsServiceImpl(CourtsRepo courtsRepo){
        this.courtsRepo = courtsRepo;
    }


    @Override
    public List<Court> getAllCourts() {
        return courtsRepo.getAllCourts();
    }

    @Override
    public Court getCourtById(int id) throws CourtNotFoundException {
        return courtsRepo.getCourtById(id);
    }

    @Override
    public void addCourt(Court newCourt) {
        courtsRepo.addCourt(newCourt);
    }

    @Override
    public void editCourt(int oldId, Court editCourt) throws CourtNotFoundException {
        Court oldCourt = getCourtById(oldId);
        oldCourt.setName(editCourt.getName());
        oldCourt.setType(editCourt.getType());
        oldCourt.setCity(editCourt.getCity());
        oldCourt.setAddress(editCourt.getAddress());
        oldCourt.setPhoneNumber(editCourt.getPhoneNumber());

        courtsRepo.addCourt(oldCourt);
    }

    @Override
    public void deleteCourt(int id) {
        courtsRepo.deleteCourt(id);
    }
}
