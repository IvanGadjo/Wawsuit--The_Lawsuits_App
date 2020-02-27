package lawsuitsapp.lawsuits.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class Court {

    int ID;

    String name;
    String type;
    String city;
    String address;
    String phoneNumber;

    // connections
    List<Document> documents;
}
