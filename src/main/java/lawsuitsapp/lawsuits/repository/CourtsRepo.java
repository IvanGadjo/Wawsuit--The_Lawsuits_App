package lawsuitsapp.lawsuits.repository;

import lawsuitsapp.lawsuits.model.Court;
import lawsuitsapp.lawsuits.model.exceptions.CourtNotFoundException;

import java.util.List;

public interface CourtsRepo {

    List<Court> getAllCourts();

    Court getCourtById(int id) throws CourtNotFoundException;

    void addCourt(Court newCourt);

    void deleteCourt(int id);
}
