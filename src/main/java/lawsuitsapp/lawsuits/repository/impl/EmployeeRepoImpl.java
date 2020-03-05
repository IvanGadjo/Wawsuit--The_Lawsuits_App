package lawsuitsapp.lawsuits.repository.impl;

import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.repository.EmployeeRepo;
import lawsuitsapp.lawsuits.repository.jpa.DocumentsRepoJPA;
import lawsuitsapp.lawsuits.repository.jpa.EmployeeRepoJPA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {

    EmployeeRepoJPA employeeRepoJPA;
    DocumentsRepoJPA documentsRepoJPA;

    public EmployeeRepoImpl(EmployeeRepoJPA employeeRepoJPA, DocumentsRepoJPA documentsRepoJPA){
        this.employeeRepoJPA = employeeRepoJPA;
        this.documentsRepoJPA = documentsRepoJPA;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepoJPA.findAll();
    }


    @Override
    public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
        return employeeRepoJPA.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public void addEmployee(Employee newEmployee) {
        employeeRepoJPA.save(newEmployee);
    }

    @Override
    public void deleteEmployee(int id) throws EmployeeNotFoundException {

        List<Document> documents = documentsRepoJPA.findAll().stream().collect(Collectors.toList());

        for (Document d:documents){
            if (d.getCreatedBy().getID() == id)
                documentsRepoJPA.delete(d);
        }

        Employee employeeToDel = getEmployeeById(id);   // ova e najbitniot del sto tuka na krajot go naogjas emp za brisenje
                                                        // da go barase porano, pred da gi izbirses negovite docs ke imase error
                    // bidejki taka bi cuval emp sto ne postoi vo db(ima docs koi vekje si gi izbrisal) pa nema da moze
                    // da bide izbrisan
        employeeRepoJPA.delete(employeeToDel);
    }


    @Override
    public void editEmployee(int oldId, Employee editedEmployee) {

        // mozebi ova treba da go smenis da bide kako vo prethodnata verzija na lawsuits app
        Employee old = employeeRepoJPA.getOne(oldId);

        // get all docs from the emp
        List<Document> documents = documentsRepoJPA.findAll().stream().filter(d ->{
            return d.getCreatedBy().getID() == oldId;
        }).collect(Collectors.toList());

        // delete all docs
        documents.stream().forEach(d -> documentsRepoJPA.delete(d));

        // change the documents list to have the new employee as creator
        documents.stream().forEach(d -> {
            d.setCreatedBy(editedEmployee);
        });

        // delete old emp
        employeeRepoJPA.delete(old);

        // save new emp
        employeeRepoJPA.save(editedEmployee);

        // save the docs
        documentsRepoJPA.saveAll(documents);
    }


}
