package tai.tutorial.proietti.platformsample;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.NodeRef;

public class EnablePlayedCiv extends PlayedCiv{
	
	    @Override
	    protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {
	        action.setParameterValue(PlayedCiv.PARAM_ACTIVE, true);
	        super.executeImpl(action, actionedUponNodeRef);
	    }
	}


