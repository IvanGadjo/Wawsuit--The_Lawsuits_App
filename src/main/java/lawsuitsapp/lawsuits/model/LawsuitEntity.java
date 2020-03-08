package lawsuitsapp.lawsuits.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "lawsuit_entities")
public class LawsuitEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;

    String name;
    int emb;        // embg or embs
    boolean isCompany;

    // connections

    // lista cases kade ovoj LawsuitEntity e tuzitel
    @OneToMany(mappedBy = "plaintiff", fetch = FetchType.EAGER)
    List<Case> casesPlaintiff;

    // lista cases kade ovoj LawsuitEntity e tuzen
    @OneToMany(mappedBy = "sued", fetch = FetchType.EAGER)
    List<Case> casesSued;


    public LawsuitEntity(String name, int emb, boolean isCompany){
        this.name = name;
        this.emb = emb;
        this.isCompany = isCompany;

        casesPlaintiff = new ArrayList<>();
        casesSued = new ArrayList<>();
    }
}
