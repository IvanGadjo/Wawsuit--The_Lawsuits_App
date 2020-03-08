package lawsuitsapp.lawsuits.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;


@Entity
@Data
@NoArgsConstructor
@Table(name = "cases")
public class Case {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;

    int caseNumber;
    String name;
    Timestamp createdAt;

    String basis;       // osnov
    float value;          // vrednost vo denari

    String phase;
    boolean isExecuted;     // true = pustena e na izvrsitel


    // connections

    //List<Employee> employees;       // nullable za vo DB        polnomosnik(od posta)
    //String proxy;              // polnomosnik (ako e drug covek sto ja tuzi posta)


    //Employee createdBy;
    @OneToMany(mappedBy = "caseId", fetch = FetchType.EAGER)
    List<Document> documents;

    //LawsuitEntity plaintiff;           // tuzitel
    //LawsuitEntity sued;            // tuzen



    @ManyToOne
    @JoinColumn(name = "parent_case_id")
    Case parentCase;

    @JsonIgnore
    @OneToMany(mappedBy = "parentCase", fetch = FetchType.EAGER)
    List<Case> childCases;


    public Case(int caseNumber,String name,String basis,float value,String phase,boolean isExecuted){
        this.caseNumber = caseNumber;
        this.name = name;
        this.basis = basis;
        this.value = value;
        this.phase = phase;
        this.isExecuted = isExecuted;

        createdAt = new Timestamp(System.currentTimeMillis());
        childCases = new ArrayList<>();
        documents = new ArrayList<>();
    }

    public void addDocument(Document document){
        documents.add(document);
    }


}
