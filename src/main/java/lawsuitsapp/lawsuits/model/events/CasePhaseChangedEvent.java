package lawsuitsapp.lawsuits.model.events;

import lawsuitsapp.lawsuits.model.Case;
import org.springframework.context.ApplicationEvent;

public class CasePhaseChangedEvent extends ApplicationEvent {

    public CasePhaseChangedEvent(Case c) {
        super(c);
    }
}
