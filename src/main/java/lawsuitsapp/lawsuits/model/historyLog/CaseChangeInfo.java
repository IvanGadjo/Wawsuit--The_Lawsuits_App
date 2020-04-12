package lawsuitsapp.lawsuits.model.historyLog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lawsuitsapp.lawsuits.model.Case;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;



@Data
@NoArgsConstructor
@Entity
@Table(name = "case_changes_info")
public class CaseChangeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;

    String changeDescription;

    LocalDateTime timeCreated;

    @JsonIgnore(true)           // should be fetchType lazy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id")
    Case theCase;

}
