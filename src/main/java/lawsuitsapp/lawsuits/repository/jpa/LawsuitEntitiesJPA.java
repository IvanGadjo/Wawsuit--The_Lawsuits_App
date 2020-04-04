package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.LawsuitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LawsuitEntitiesJPA extends JpaRepository<LawsuitEntity, Integer> {

    @Query("select le from LawsuitEntity le " +
            "where le.name like %:term%")
    List<LawsuitEntity> searchLawsuitEntities(@Param("term") String term);
}
