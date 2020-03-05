package lawsuitsapp.lawsuits.dataholder;


import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Court;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.repository.jpa.CasesRepoJPA;
import lawsuitsapp.lawsuits.repository.jpa.CourtsRepoJPA;
import lawsuitsapp.lawsuits.repository.jpa.DocumentsRepoJPA;
import lawsuitsapp.lawsuits.repository.jpa.EmployeeRepoJPA;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class DataHolder {

    EmployeeRepoJPA employeeRepoJPA;
    DocumentsRepoJPA documentsRepoJPA;
    CourtsRepoJPA courtsRepoJPA;
    CasesRepoJPA casesRepoJPA;

    List<Employee> initialEmployees;
    List<Document> initialDocs;
    List<Court> initialCourts;
    List<Case> initialCases;

    public DataHolder(EmployeeRepoJPA employeeRepoJPA, DocumentsRepoJPA documentsRepoJPA, CourtsRepoJPA courtsRepoJPA,
                      CasesRepoJPA casesRepoJPA){
        this.employeeRepoJPA = employeeRepoJPA;
        this.documentsRepoJPA = documentsRepoJPA;
        this.courtsRepoJPA = courtsRepoJPA;
        this.casesRepoJPA = casesRepoJPA;

        initialEmployees = new ArrayList<>();
        initialDocs = new ArrayList<>();
        initialCourts = new ArrayList<>();
        initialCases = new ArrayList<>();

        generateData();
        //fillDBAtStart();
    }

    public void generateData(){
        // employees
        Employee emp1 = new Employee("User","First","usrfirst","usrfirstpass","lawyer");
        Employee emp2 = new Employee("User","Second","usrsecond","usrsecondpass","lawyer");


        // courts
        Court c1 = new Court("osnoven SK","osnoven","Skopje","prva 123/1","02123456");



        // cases
        Case pCase = new Case(11,"firstCase","basis1",200,"first",false);

        Case cCase1 = new Case(12,"secondCase","basis2",220,"first",false);
        Case cCase2 = new Case(13,"thirdCase","basis3",270,"first",false);

        cCase1.setParentCase(pCase);
        cCase2.setParentCase(pCase);


        // documents
        Document doc1 = new Document("firstDoc",101,true,
                LocalDate.of(2020,1,1),".docx",null,emp1,c1,pCase);
        Document doc2 = new Document("secondDoc",102,false,
                LocalDate.of(2020,1,2),".docx",null,emp2,c1,cCase1);
        Document doc3 = new Document("thirdDoc",103,true,
                LocalDate.of(2020,1,3),".docx",null,emp2,c1,cCase1);





        emp1.addDocument(doc1);
        emp2.addDocument(doc2);
        emp2.addDocument(doc3);


        initialEmployees.add(emp1);
        initialEmployees.add(emp2);

        initialDocs.add(doc1);
        initialDocs.add(doc2);
        initialDocs.add(doc3);

        initialCourts.add(c1);

        initialCases.add(pCase);
        initialCases.add(cCase1);
        initialCases.add(cCase2);
    }

    public void fillDBAtStart(){

        if(employeeRepoJPA.count()==0) {
            employeeRepoJPA.saveAll(initialEmployees);
            documentsRepoJPA.saveAll(initialDocs);
            courtsRepoJPA.saveAll(initialCourts);
            casesRepoJPA.saveAll(initialCases);
        }
        else{
            System.out.println("DB already not empty");
        }
    }
}