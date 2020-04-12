package lawsuitsapp.lawsuits.repository.impl;

import lawsuitsapp.lawsuits.model.Court;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.exceptions.CourtNotFoundException;
import lawsuitsapp.lawsuits.repository.CourtsRepo;
import lawsuitsapp.lawsuits.repository.jpa.CourtsRepoJPA;
import lawsuitsapp.lawsuits.repository.jpa.DocumentsRepoJPA;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourtsRepoImpl implements CourtsRepo {

    CourtsRepoJPA courtsRepoJPA;

    public CourtsRepoImpl(CourtsRepoJPA courtsRepoJPA,DocumentsRepoJPA documentsRepoJPA){
        this.courtsRepoJPA = courtsRepoJPA;
    }

    @Override
    public List<Court> getAllCourts() {
        return courtsRepoJPA.findAll();
    }

    @Override
    public Court getCourtById(int id) throws CourtNotFoundException {
        return courtsRepoJPA.findById(id).orElseThrow(CourtNotFoundException::new);
    }

    @Override
    public void addCourt(Court newCourt) {
        courtsRepoJPA.save(newCourt);
    }

    // todo: not implemented

    // koga se brise court treba site docs sto se od nego da im se stavi null vo nivnata tabela
    @Override
    public void deleteCourt(int id) {

    }







}
