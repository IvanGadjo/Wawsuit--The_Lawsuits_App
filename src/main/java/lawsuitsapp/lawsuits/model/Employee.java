package lawsuitsapp.lawsuits.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;

    String firstName;
    String lastName;

    // for authentication
    String username;
    String password;
    String role;

    // connections
    //fixme
    @ManyToMany(fetch = FetchType.EAGER)
    List<Case> cases;

    //fixme
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)             // radi ova se poprava problemot so MultipleBagFetchException https://stackoverflow.com/questions/4334970/hibernate-throws-multiplebagfetchexception-cannot-simultaneously-fetch-multipl
    List<Case> createdCases;

    @OneToMany(mappedBy = "createdBy",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Document> documents;
    //Credentials credentials;





    public Employee(String firstName,String lastName,String username,String password,String role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;

        documents = new ArrayList<>();
        cases = new ArrayList<>();
        createdCases = new ArrayList<>();
    }

    public void addDocument(Document document){
        documents.add(document);
    }




    public void addCase(Case theCase){
        cases.add(theCase);
    }

    public void removeCase(Case theCase){
        cases.remove(theCase);
        //cases.clear();


        // print method for debugging
        //cases.forEach(c -> System.out.println("YE  "+c.getID()));
    }


    @Override
    public boolean equals(Object otherObject){
        Employee otherEmployee = (Employee) otherObject;
        if(this.getID() == otherEmployee.getID())
            return true;
        else
            return false;
    }

}
