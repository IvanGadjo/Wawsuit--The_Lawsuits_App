package lawsuitsapp.lawsuits.model.events;

import lawsuitsapp.lawsuits.model.Case;
import org.springframework.context.ApplicationEvent;

public class CaseAddedEmployeesEvent extends ApplicationEvent {
    public CaseAddedEmployeesEvent(Case c) {
        super(c);
    }
}
