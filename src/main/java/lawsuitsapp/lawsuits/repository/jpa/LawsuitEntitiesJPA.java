package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.LawsuitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawsuitEntitiesJPA extends JpaRepository<LawsuitEntity, Integer> {
}
