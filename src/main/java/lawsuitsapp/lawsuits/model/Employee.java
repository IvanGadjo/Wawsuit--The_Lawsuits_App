package lawsuitsapp.lawsuits.model;


import lombok.Data;
import lombok.NoArgsConstructor;

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
    //List<Case> cases;
    //List<Case> createdCases;
    @OneToMany(mappedBy = "createdBy",fetch = FetchType.EAGER)
    List<Document> documents;
    //Credentials credentials;


    public Employee(String firstName,String lastName,String username,String password,String role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;

        documents = new ArrayList<>();
    }

    public void addDocument(Document document){
        documents.add(document);
    }

}
