package lawsuitsapp.lawsuits.service.impl;


import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.Document;
import lawsuitsapp.lawsuits.model.Employee;
import lawsuitsapp.lawsuits.model.exceptions.CaseNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.DocumentNotFoundException;
import lawsuitsapp.lawsuits.model.exceptions.EmployeeNotFoundException;
import lawsuitsapp.lawsuits.repository.CasesRepo;
import lawsuitsapp.lawsuits.repository.DocumentsRepo;
import lawsuitsapp.lawsuits.service.CasesService;
import lawsuitsapp.lawsuits.service.DocumentsService;
import lawsuitsapp.lawsuits.service.EmployeeService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CasesServiceImpl implements CasesService {

    CasesRepo casesRepo;
    DocumentsRepo documentsRepo;
    DocumentsService documentsService;
    EmployeeService employeeService;

    public CasesServiceImpl(CasesRepo casesRepo, DocumentsRepo documentsRepo, DocumentsService documentsService,
                            @Lazy EmployeeService employeeService){
        this.casesRepo = casesRepo;
        this.documentsRepo = documentsRepo;
        this.documentsService = documentsService;
        this.employeeService = employeeService;
    }


    @Override
    public List<Case> getAllCases() {
        return casesRepo.getAllCases();
    }

    @Override
    public Case getCaseById(int id) throws CaseNotFoundException {
        return casesRepo.getCaseById(id);
    }

    @Override
    public void addCase(Case newCase) {
        casesRepo.addCase(newCase);
    }


    /*
    fixme: sredi to se desava so lawsuitEntities(mislam ne treba niso za ova da se sredi), employees
     */
    @Override
    public void deleteCase(int id) throws CaseNotFoundException {
        Case caseToDelete = casesRepo.getCaseById(id);
        boolean isParent = true;

        // check if it is a parent case
        if(caseToDelete.getParentCase() != null)
            isParent = false;



        if(isParent){       // it is parent
            List<Case> childCases = getAllCasesByParentCaseId(id);

            // set docIds to null of all child cases
            childCases.stream().forEach(cc ->{
                try {
                    documentsService.getAllDocumentsOfCaseById(cc.getID()).stream().forEach(d ->{
                        try {
                            documentsService.setCaseIdToNull(d.getID());
                        } catch (DocumentNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (CaseNotFoundException e) {
                    e.printStackTrace();
                }
            });

            // remove case from the cases list of all of the employees working on the case
            childCases.stream().forEach(cc ->{

                try {
                    employeeService.getEmployeesByCaseId(cc.getID()).stream().forEach(e ->{
                        try {
                            employeeService.removeCaseFromEmployee(e.getID(),cc.getID());
                        } catch (EmployeeNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (CaseNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    });
                } catch (CaseNotFoundException e) {
                    e.printStackTrace();
                }
            });


            // re-read child cases (now without docs)
            childCases = getAllCasesByParentCaseId(id);

            // delete child cases
            childCases.stream().forEach(cc -> {
                try {
                    deleteCase(cc.getID());
                } catch (CaseNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }


        // set all caseId to all of the docs to null
        documentsService.getAllDocumentsOfCaseById(id).stream().forEach(d ->{
            try {
                documentsService.setCaseIdToNull(d.getID());
            } catch (DocumentNotFoundException e) {
                e.printStackTrace();
            }
        });





        // re-read the case (now without the child cases)
        caseToDelete = casesRepo.getCaseById(id);

        // remove the case from the cases list of all the employees working on the case
        employeeService.getEmployeesByCaseId(caseToDelete.getID()).stream().forEach(e ->{
            try {
                employeeService.removeCaseFromEmployee(e.getID(),id);
            } catch (EmployeeNotFoundException ex) {
                ex.printStackTrace();
            } catch (CaseNotFoundException ex) {
                ex.printStackTrace();
            }
        });




        // delete the case
        casesRepo.deleteCase(id);
    }

    @Override
    public List<Case> getAllCasesByParentCaseId(int parentCaseId) throws CaseNotFoundException {
        List<Case> cases = casesRepo.getCaseById(parentCaseId).getChildCases();
        List<Case> finalList = new ArrayList<>();
        for (Case c:cases){
            if(!finalList.contains(c))
                finalList.add(c);
        }

        return finalList;
    }


    // fixme: OK
    @Override
    public void editCase(int oldId, Case editedCase) throws CaseNotFoundException {
        Case oldCase = casesRepo.getCaseById(oldId);

        oldCase.setCaseNumber(editedCase.getCaseNumber());
        oldCase.setName(editedCase.getName());
        oldCase.setBasis(editedCase.getBasis());
        oldCase.setValue(editedCase.getValue());
        oldCase.setPhase(editedCase.getPhase());
        oldCase.setExecuted(editedCase.isExecuted());
        oldCase.setPlaintiff(editedCase.getPlaintiff());
        oldCase.setSued(editedCase.getSued());
        oldCase.setCreatedBy(editedCase.getCreatedBy());
        oldCase.setProxy(editedCase.getProxy());

        casesRepo.addCase(oldCase);
    }

    @Override
    public void moveDocumentsBetweenCases(int idTo, List<Integer> documentIds) {
        List<Document> documents = new ArrayList<>();

        // get all documents
        documentIds.stream().forEach(di -> {
            try {
                documents.add(documentsService.getDocumentById(di));
            } catch (DocumentNotFoundException e) {
                e.printStackTrace();
            }
        });

        // change the caseId of all the docs
        documents.stream().forEach(d -> {
            try {
                d.setCaseId(casesRepo.getCaseById(idTo));
            } catch (CaseNotFoundException e) {
                e.printStackTrace();
            }
        });

        // save the docs (updated)
        documents.stream().forEach(d -> documentsService.addDocument(d));

    }

    @Override
    public void changePhaseOfCase(int id, String newPhase) throws CaseNotFoundException {
        Case oldCase = casesRepo.getCaseById(id);

        oldCase.setPhase(newPhase);

        casesRepo.addCase(oldCase);
    }

    @Override
    public void setPlaintiffToNull(int caseId) throws CaseNotFoundException {
        Case theCase = casesRepo.getCaseById(caseId);
        theCase.setPlaintiff(null);
        casesRepo.addCase(theCase);
    }

    @Override
    public void setSuedToNull(int caseId) throws CaseNotFoundException{
        Case theCase = casesRepo.getCaseById(caseId);
        theCase.setSued(null);
        casesRepo.addCase(theCase);
    }

    @Override
    public void setEmployeeCreatorToNull(int caseId) throws CaseNotFoundException {
        Case theCase = getCaseById(caseId);
        theCase.setCreatedBy(null);
        casesRepo.addCase(theCase);
    }

    // todo: stavi proverka da ne moze da se stai employee sto vekje e staven
    // fixme
    @Override
    public void addEmployeesToCase(int caseId, List<Integer> employeeIds) throws CaseNotFoundException {
        // get the case
        Case theCase = getCaseById(caseId);

        // get the employees
        List<Employee> employees = new ArrayList<>();
        employeeIds.forEach(ei -> {
            try {
                employees.add(employeeService.getEmployeeById(ei));
            } catch (EmployeeNotFoundException e) {
                e.printStackTrace();
            }
        });

        // add them to the case
        employees.forEach(e -> theCase.addEmployee(e));



        /* todo: Pri M-N vrski sekoj update mora da go napravis i na dvete strani, zatoa
            i kaj employees go stavas case-ot
         */
        employees.forEach(e -> {
            try {
                employeeService.addCaseToEmployee(e.getID(),theCase.getID());
            } catch (EmployeeNotFoundException ex) {
                ex.printStackTrace();
            } catch (CaseNotFoundException ex) {
                ex.printStackTrace();
            }
        });


        // save updated case
        addCase(theCase);
    }


    // fixme
    @Override
    public void removeEmployeesFromCase(int caseId, List<Integer> employeeIds) throws CaseNotFoundException {
        // get the case
        Case theCase = getCaseById(caseId);

        // get the employees
        List<Employee> employees = new ArrayList<>();
        employeeIds.forEach(ei -> {
            try {
                employees.add(employeeService.getEmployeeById(ei));
            } catch (EmployeeNotFoundException e) {
                e.printStackTrace();
            }
        });



        // remove them from the case
        employees.forEach(e -> theCase.removeEmployee(e));

        // because of M-N relationship
       employees.forEach(e -> {
           try {
               employeeService.removeCaseFromEmployee(e.getID(),theCase.getID());
           } catch (EmployeeNotFoundException ex) {
               ex.printStackTrace();
           } catch (CaseNotFoundException ex) {
               ex.printStackTrace();
           }
       });





        // save updated case
        addCase(theCase);

    }

    @Override
    public List<Case> getCasesByEmployeeId(int employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId);

        return employee.getCases();
    }

    // todo: implement
    @Override
    public void changeParentCaseOfCase(int caseId, int parentCaseId) {

    }

}
