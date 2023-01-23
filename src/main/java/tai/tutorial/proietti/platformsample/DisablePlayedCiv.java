package tai.tutorial.proietti.platformsample;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.action.Action;

public class DisablePlayedCiv extends PlayedCiv {
    @Override
    protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {
        action.setParameterValue(PlayedCiv.PARAM_ACTIVE, false);
        super.executeImpl(action, actionedUponNodeRef);
    }
}
