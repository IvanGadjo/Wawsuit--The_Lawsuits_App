package lawsuitsapp.lawsuits.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
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
    //List<Document> documents;

    //LawsuitEntity plaintiff;           // tuzitel
    //LawsuitEntity sued;            // tuzen

    //todo: ova e self referencing, najdi kako se pravi
    //List<Case> childCases;


}
