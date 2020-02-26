package lawsuitsapp.lawsuits.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Court {

    int ID;

    String name;
    String type;
    String city;
    String address;
    String phoneNumber;
}
