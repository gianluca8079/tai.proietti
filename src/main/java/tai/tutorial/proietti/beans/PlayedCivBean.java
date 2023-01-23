package tai.tutorial.proietti.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.rest.api.model.Action;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tai.tutorial.proietti.platformsample.CreateFolderCiv;
import tai.tutorial.proietti.platformsample.Parametri;

public class PlayedCivBean {
	// Dependencies
    private NodeService nodeService;
	private static Log logger = LogFactory.getLog(CreateFolderCiv.class);  

    
    

    public void create(final NodeRef nodeRef, final String nodeName) {
    	
    
    	QName associationQName = QName.createQName(Parametri.URI,"assoc");

    	Map<QName, Serializable> props = new HashMap<QName, Serializable>();
        props.put(Parametri.CIV_PROP_CIVILIZATION_NAME,nodeName);
        nodeService.createNode(nodeRef,ContentModel.ASSOC_CONTAINS,associationQName,Parametri.CIV_PROP_CIVILIZATION_NAME,props);
    }

    public NodeService getNodeService() {
        return nodeService;
    }


    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }    

}
