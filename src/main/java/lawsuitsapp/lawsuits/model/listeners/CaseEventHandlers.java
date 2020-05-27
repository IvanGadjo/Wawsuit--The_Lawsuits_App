package lawsuitsapp.lawsuits.model.listeners;

import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.events.CaseCreatedEvent;
import lawsuitsapp.lawsuits.model.events.CaseDeletedEvent;
import lawsuitsapp.lawsuits.model.historyLog.CaseChangeInfo;
import lawsuitsapp.lawsuits.service.CaseChangeInfoService;
import lawsuitsapp.lawsuits.service.CasesService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CaseEventHandlers {

    private final CaseChangeInfoService caseChangeInfoService;

    public CaseEventHandlers(CaseChangeInfoService caseChangeInfoService){
        this.caseChangeInfoService = caseChangeInfoService;
    }


    @EventListener
    public void onCaseCreated(CaseCreatedEvent caseCreatedEvent){
        String name = ((Case)caseCreatedEvent.getSource()).getName();
        int id = ((Case)caseCreatedEvent.getSource()).getID();

        // add to caseChangeinfoRepo
        CaseChangeInfo caseChangeInfo = new CaseChangeInfo();
        caseChangeInfo.setChangeDescription("Added new case: "+name+" ID: "+id);
        caseChangeInfo.setTheCase((Case)caseCreatedEvent.getSource());
        caseChangeInfo.setTimeCreated(LocalDateTime.now());

        caseChangeInfoService.addChange(caseChangeInfo);

    }

    @EventListener
    public void onCaseDeleted(CaseDeletedEvent caseDeletedEvent){
        String name = ((Case) caseDeletedEvent.getSource()).getName();
        int id = ((Case)caseDeletedEvent.getSource()).getID();

        // change FK of every record of the case to null
        CaseChangeInfo caseChangeInfo = new CaseChangeInfo();
        caseChangeInfo.setChangeDescription("Deleted case: "+name+" ID: "+id);
        caseChangeInfo.setTheCase((Case)caseDeletedEvent.getSource());
        caseChangeInfo.setTimeCreated(LocalDateTime.now());

        caseChangeInfoService.addChange(caseChangeInfo);


        // add new record of this deletion, with a null for FK
        caseChangeInfoService.setCaseIdToNull(id);
    }
}
