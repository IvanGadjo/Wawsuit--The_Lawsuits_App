package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtsRepoJPA extends JpaRepository<Court,Integer> {
}
