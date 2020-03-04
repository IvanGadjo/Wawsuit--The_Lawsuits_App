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
@Table(name = "courts")
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;

    String name;
    String type;
    String city;
    String address;
    String phoneNumber;

    // connections
    @OneToMany(mappedBy = "court",fetch = FetchType.EAGER)
    List<Document> documents;


    public Court(String name,String type,String city,String address,String phoneNumber){
        this.name = name;
        this.type = type;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;

        documents = new ArrayList<>();
    }

    public void addDocument(Document document){
        documents.add(document);
    }
}
