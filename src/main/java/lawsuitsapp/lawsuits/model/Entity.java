package lawsuitsapp.lawsuits.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Entity {

    int ID;
    String name;
    int emb;        // embg or embs
    boolean isCompany;
}
