package tai.tutorial.proietti.platformsample;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PlayedCiv extends ActionExecuterAbstractBase {
	
	public final static String PARAM_ACTIVE = "active";
	private NodeService nodeService;
	private static Log logger = LogFactory.getLog(PlayedCiv.class);  



	@Override
	protected void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		Boolean activeFlag = (Boolean)action.getParameterValue(PARAM_ACTIVE);

	    if (activeFlag == null) activeFlag = false; // default

	    // set the sc:isActive property to true
	    Map<QName, Serializable> properties = nodeService.getProperties(actionedUponNodeRef);
	    properties.put(Parametri.CIV_PROP_IS_PLAYED, activeFlag);
	    properties.put(Parametri.CIV_PROP_DATE_OF_PLAY, null);
	    properties.put(Parametri.CIV_PROP_NUMBER_OF_TURNS, null);
	    properties.put(Parametri.CIV_PROP_TYPE_OF_VICTORY, null);

	    

	    if (activeFlag) {
	        // otherwise, add the aspect and set the properties
	        nodeService.addAspect(actionedUponNodeRef,Parametri.CIV_ASPECT_PLAYED_CIV, properties);
	        logger.debug("Aspetto aggiunto");
	        
	    }
	    
	    else {
	    	if(nodeService.hasAspect(actionedUponNodeRef, Parametri.CIV_ASPECT_PLAYED_CIV)) {
	    		nodeService.removeAspect(actionedUponNodeRef, Parametri.CIV_ASPECT_PLAYED_CIV);
		        logger.debug("Aspetto rimosso");

	    	}
	    }
	    
	}

	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		paramList.add(
				new ParameterDefinitionImpl(
						PARAM_ACTIVE,
						DataTypeDefinition.BOOLEAN,
						false,
						getParamDisplayLabel(PARAM_ACTIVE)));		
	}
	
	
	
	/**
	* @param nodeService The NodeService to set.
	*/
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}


}
