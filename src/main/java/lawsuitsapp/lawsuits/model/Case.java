package lawsuitsapp.lawsuits.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    //fixme
    @JsonIgnore
    @ManyToMany(mappedBy = "cases", fetch = FetchType.EAGER)
    List<Employee> employees;       // nullable za vo DB        polnomosnik(od posta)

    //fixme
    String proxy;              // polnomosnik (od drugata firma)

    //fixme
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_creator_Id")
    Employee createdBy;

    @OneToMany(mappedBy = "caseId", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Document> documents;

    @JsonIgnore
    @ManyToOne
    LawsuitEntity plaintiff;           // tuzitel

    @JsonIgnore
    @ManyToOne
    LawsuitEntity sued;            // tuzen



    @ManyToOne
    @JoinColumn(name = "parent_case_id")
    Case parentCase;

    @JsonIgnore
    @OneToMany(mappedBy = "parentCase", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Case> childCases;


    public Case(int caseNumber,String name,String basis,float value,String phase,boolean isExecuted,
                LawsuitEntity plaintiff, LawsuitEntity sued, Employee createdBy, String proxy){
        this.caseNumber = caseNumber;
        this.name = name;
        this.basis = basis;
        this.value = value;
        this.phase = phase;
        this.isExecuted = isExecuted;
        this.plaintiff = plaintiff;
        this.sued = sued;
        this.createdBy = createdBy;
        this.proxy = proxy;

        createdAt = new Timestamp(System.currentTimeMillis());
        childCases = new ArrayList<>();
        documents = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public void addDocument(Document document){
        documents.add(document);
    }




    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }


    // needed for comparison in Employee->remove case
    @Override
    public boolean equals(Object otherCaseObj) {
        Case otherCase = (Case) otherCaseObj;
        if(this.getID() == otherCase.getID())
            return true;
        else
            return false;
    }

}
