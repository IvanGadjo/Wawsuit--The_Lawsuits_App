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
    DocumentsRepoJPA documentsRepoJPA;

    public CourtsRepoImpl(CourtsRepoJPA courtsRepoJPA,DocumentsRepoJPA documentsRepoJPA){
        this.courtsRepoJPA = courtsRepoJPA;
        this.documentsRepoJPA = documentsRepoJPA;
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

    @Override
    public void editCourt(int oldId, Court editCourt) throws CourtNotFoundException {

        Court oldCourt = courtsRepoJPA.getOne(oldId);

        // get all docs by this court
        List<Document> documents = documentsRepoJPA.findAll().stream().filter(d ->{
            return d.getCourt().getID() == oldId;
        }).collect(Collectors.toList());

        // delete all docs from repo
        documents.stream().forEach(d -> documentsRepoJPA.delete(d));

        // change the docs list to have the new court as a court
        documents.stream().forEach(d ->{
            d.setCourt(editCourt);
        });

        // delete old court
        courtsRepoJPA.delete(oldCourt);

        // save new court
        courtsRepoJPA.save(editCourt);

        // save the docs
        documentsRepoJPA.saveAll(documents);

        // todo: Na ovoj nacin mozes isto, ova e so findById mesto so getOne za naogjanje na oldCourt

//        Court oldCourt = courtsRepoJPA.findById(oldId).orElseThrow(CourtNotFoundException::new);
//
//        // get all docs by this court
//        List<Document> documents = documentsRepoJPA.findAll().stream().filter(d ->{
//            return d.getCourt().getID() == oldId;
//        }).collect(Collectors.toList());
//
//        // delete all docs from repo
//        documents.stream().forEach(d -> documentsRepoJPA.delete(d));
//
//        // change the docs list to have the new court as a court
//        documents.stream().forEach(d ->{
//            d.setCourt(editCourt);
//        });
//
//        oldCourt.setDocuments(new ArrayList<>());
//
//        // delete old court
//        courtsRepoJPA.delete(oldCourt);
//
//        // save new court
//        courtsRepoJPA.save(editCourt);
//
//        // save the docs
//        documentsRepoJPA.saveAll(documents);
    }

    // todo: not implemented
    // koga se brise court treba site docs sto se od nego da im se stavi null vo nivnata tabela
    @Override
    public void deleteCourt(int id) {

    }
}
