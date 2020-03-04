package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsRepoJPA extends JpaRepository<Document,Integer> {
}
