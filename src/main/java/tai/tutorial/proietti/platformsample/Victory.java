package tai.tutorial.proietti.platformsample;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.alfresco.repo.jscript.Behaviour;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;

public class Victory implements NodeServicePolicies.OnAddAspectPolicy {
	
	// Dependencies
	private NodeService nodeService;
	private PolicyComponent policyComponent;

	// Behaviours
	private JavaBehaviour onAddAspect;
	
	Map<QName,Serializable> properties = new HashMap<>();
	
	
	
	@Override
	public void onAddAspect(NodeRef nodeRef, QName aspectTypeQName) {
		String typeOfVictory = getTypeOfVictory();
		int numberOfTurns = getNumberOfTurns();
		Date dateOfPlay = getDateOfPlay();
		properties.put(Parametri.CIV_PROP_TYPE_OF_VICTORY,typeOfVictory);
		properties.put(Parametri.CIV_PROP_NUMBER_OF_TURNS, numberOfTurns);
		properties.put(Parametri.CIV_PROP_IS_PLAYED, true);
		properties.put(Parametri.CIV_PROP_DATE_OF_PLAY, dateOfPlay);
		nodeService.addProperties(nodeRef, properties);
		
	    

		
	}
	
	
	
	private Date getDateOfPlay() {
	    java.util.Date date = new java.util.Date();    
		return date;
	}



	private int getNumberOfTurns() {
        Random random = new Random();
        int result = random.nextInt(300);
        return result + 200;
}



	private String getTypeOfVictory() {
        Random random = new Random();
        int randomIndex = random.nextInt(Parametri.victoryType.size());
        return Parametri.victoryType.get(randomIndex);
	}
	
	
	
	
	
	
	
	
	

	public void init() {

	    // Create behaviours
	    this.onAddAspect = new JavaBehaviour(this, "onAddAspect", NotificationFrequency.EVERY_EVENT);


	    // Bind behaviours to node policies
	    this.policyComponent.bindClassBehaviour(
	        QName.createQName(NamespaceService.ALFRESCO_URI, "onAddAspect"),
	     Parametri.CIV_ASPECT_PLAYED_CIV,
	        this.onAddAspect
	    );


	}
	
	
	public NodeService getNodeService() {
		return nodeService;
	}


	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}


	public PolicyComponent getPolicyComponent() {
		return policyComponent;
	}


	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}


}
