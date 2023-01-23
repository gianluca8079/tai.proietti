package tai.tutorial.proietti.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

import tai.tutorial.proietti.platformsample.Parametri;

public class DeletePlayedCivBean {
	// Dependencies
    private NodeService nodeService;

    public void delete(final NodeRef nodeRef,final String nodeName) {
    	NodeRef nodo = nodeService.getChildByName(nodeRef, ContentModel.ASSOC_CONTAINS, nodeName);
		if(nodo != null) {
			nodeService.deleteNode(nodo);
    		
    	}
    
    
    
    }
    
       

    public NodeService getNodeService() {
        return nodeService;
    }


    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }    

}
