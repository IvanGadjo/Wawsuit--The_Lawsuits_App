package lawsuitsapp.lawsuits.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
public class Case {

    int ID;

    int caseNumber;
    String name;
    Timestamp createdAt;

    String basis;       // osnov
    float value;          // vrednost vo denari

    String phase;
    boolean isExecuted;     // true = pustena e na izvrsitel


    // connections

    List<Employee> employees;       // nullable za vo DB        polnomosnik(od posta)
    String  proxy;              // polnomosnik (ako e drug covek sto ja tuzi posta)


    Employee createdBy;
    List<Document> documents;

    Entity plaintiff;           // tuzitel
    Entity sued;            // tuzen


}
