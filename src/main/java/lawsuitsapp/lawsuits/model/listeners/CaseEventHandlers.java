package lawsuitsapp.lawsuits.model.listeners;

import lawsuitsapp.lawsuits.model.Case;
import lawsuitsapp.lawsuits.model.events.CaseCreatedEvent;
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

        // add to caseChangeinfoRepo
        CaseChangeInfo caseChangeInfo = new CaseChangeInfo();
        caseChangeInfo.setChangeDescription("Added new case: "+name);
        caseChangeInfo.setTheCase((Case)caseCreatedEvent.getSource());
        caseChangeInfo.setTimeCreated(LocalDateTime.now());

        caseChangeInfoService.addChange(caseChangeInfo);

    }
}
