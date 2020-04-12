package lawsuitsapp.lawsuits.model.events;

import lawsuitsapp.lawsuits.model.Case;
import org.springframework.context.ApplicationEvent;

public class CaseRemovedEmployeesEvent extends ApplicationEvent {
    public CaseRemovedEmployeesEvent(Case c) {
        super(c);
    }
}
