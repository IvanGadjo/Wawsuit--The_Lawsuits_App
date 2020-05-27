package lawsuitsapp.lawsuits.model.events;

import lawsuitsapp.lawsuits.model.Case;
import org.springframework.context.ApplicationEvent;

public class CaseDeletedEvent extends ApplicationEvent {

    public CaseDeletedEvent(Case c) {
        super(c);
    }
}
