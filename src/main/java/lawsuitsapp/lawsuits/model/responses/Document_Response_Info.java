package lawsuitsapp.lawsuits.model.responses;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Document_Response_Info {

    int id;
    String name;
    int archiveNumber;
    boolean isInput;
    LocalDate documentDate;
    String fileType;
    String employeeCreatorName;

    String downloadUrl;


    public Document_Response_Info(int id,String name,int archiveNumber, boolean isInput, LocalDate documentDate,
                                  String fileType, String employeeCreatorName){
        this.id = id;
        this.name = name;
        this.archiveNumber = archiveNumber;
        this.isInput = isInput;
        this.documentDate = documentDate;
        this.fileType = fileType;
        this.employeeCreatorName = employeeCreatorName;

        downloadUrl = "http://localhost:8080/documents/downloadDocument/"+id;
    }
}
