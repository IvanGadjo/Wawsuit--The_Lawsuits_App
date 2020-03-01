package lawsuitsapp.lawsuits.dataholder;


import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.repository.EmployeeRepo;
import lawsuitsapp.lawsuits.repository.EmployeeRepoJPA;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class DataHolder {

    EmployeeRepoJPA employeeRepoJPA;
    List<Employee> initialEmployees;

    public DataHolder(EmployeeRepoJPA employeeRepoJPA){
        this.employeeRepoJPA = employeeRepoJPA;
        initialEmployees = new ArrayList<>();

        generateData();
    }

    public void generateData(){
        Employee emp1 = new Employee("User","First","usrfirst","usrfirstpass","lawyer");
        Employee emp2 = new Employee("User","Second","usrsecond","usrsecondpass","lawyer");

        Document doc1 = new Document("firstDoc",101,true,
                LocalDate.of(2020,1,1),".docx",null);
        Document doc2 = new Document("secondDoc",102,false,
                LocalDate.of(2020,1,2),".docx",null);
        Document doc3 = new Document("thirdDoc",103,true,
                LocalDate.of(2020,1,3),".docx",null);


        emp1.addDocument(doc1);
        emp2.addDocument(doc2);
        emp2.addDocument(doc3);


        initialEmployees.add(emp1);
        initialEmployees.add(emp2);
    }

    public void fillDBAtStart(){
        if(employeeRepoJPA.count()==0) {
            employeeRepoJPA.saveAll(initialEmployees);
        }
        else{
            System.out.println("DB already not empty");
        }
    }
}