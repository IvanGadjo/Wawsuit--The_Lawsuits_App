package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepoJPA extends JpaRepository<Employee,Integer> {

}
