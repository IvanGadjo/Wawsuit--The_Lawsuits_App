package lawsuitsapp.lawsuits.model.events;

import lawsuitsapp.lawsuits.model.Case;
import org.springframework.context.ApplicationEvent;

public class CaseEditedEvent extends ApplicationEvent {
    public CaseEditedEvent(Case c) {
        super(c);
    }
}
