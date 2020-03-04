package lawsuitsapp.lawsuits.service;

import lawsuitsapp.lawsuits.model.Court;
import lawsuitsapp.lawsuits.model.exceptions.CourtNotFoundException;

import java.util.List;

public interface CourtsService {

    List<Court> getAllCourts();

    Court getCourtById(int id) throws CourtNotFoundException;

    void addCourt(Court newCourt);

    void editCourt(int oldId, Court editCourt) throws CourtNotFoundException;

    void deleteCourt(int id);
}
