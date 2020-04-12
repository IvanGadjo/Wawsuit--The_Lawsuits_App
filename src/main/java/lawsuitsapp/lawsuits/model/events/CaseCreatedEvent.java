package lawsuitsapp.lawsuits.model.events;

import lawsuitsapp.lawsuits.model.Case;
import org.springframework.context.ApplicationEvent;

public class CaseCreatedEvent extends ApplicationEvent {

    public CaseCreatedEvent(Case c) {
        super(c);
    }
}
