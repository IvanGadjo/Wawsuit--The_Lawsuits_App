package lawsuitsapp.lawsuits.repository.jpa;

import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.LawsuitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepoJPA extends JpaRepository<Employee,Integer> {

    Employee findByUsername(String username);


    @Query("select e from Employee e " +
            "where e.firstName like %:term% " +
            "or e.lastName like %:term%")
    List<Employee> searchEmployees(@Param("term") String term);
}
