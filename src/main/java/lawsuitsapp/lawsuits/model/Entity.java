package lawsuitsapp.lawsuits.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@Data
@NoArgsConstructor
public class Entity {

    int ID;

    String name;
    int emb;        // embg or embs
    boolean isCompany;

    // connections
    List<Case> cases;
}
