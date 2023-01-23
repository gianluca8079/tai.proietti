package tai.tutorial.proietti.beans;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

import tai.tutorial.proietti.platformsample.Parametri;

public class AddVictoryPlayedCivBean {
	
    private NodeService nodeService;


	public void addAspect(NodeRef curNode) {
		if(nodeService.hasAspect(curNode, Parametri.CIV_ASPECT_PLAYED_CIV))
			nodeService.removeAspect(curNode, Parametri.CIV_ASPECT_PLAYED_CIV);
			else{
				nodeService.addAspect(curNode, Parametri.CIV_ASPECT_PLAYED_CIV, null);
			};
		
	}
	
	public NodeService getNodeService() {
        return nodeService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }


}
