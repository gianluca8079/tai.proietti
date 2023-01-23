package tai.tutorial.proietti.scripts;

import java.util.HashMap;
import java.util.Map;

import org.springframework.extensions.webscripts.Status;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;

import tai.tutorial.proietti.beans.PlayedCivBean;

	
	public class PostPlayedCiv extends DeclarativeWebScript {

	    private PlayedCivBean playedCivBean;
	    private NodeService nodeService;

	    @Override
	    protected Map<String, Object> executeImpl(WebScriptRequest req,Status status) {
	        String id = req.getParameter("id");
	        String nodeName = req.getParameter("nodeName");

	        if (id == null || nodeName == null) {
	            status.setCode(400, "Required data has not been provided");
	            status.setRedirect(true);
	        } else {
	            NodeRef curNode = new NodeRef("workspace://SpacesStore/" + id);
	            if (!nodeService.exists(curNode)) {
	                status.setCode(404, "No node found for id:" + id);
	                status.setRedirect(true);
	            } else {
	                playedCivBean.create(curNode, nodeName);
	            }

	        }

	        Map<String, Object> model = new HashMap<String, Object>();
	        model.put("node", id);
	        model.put("nodeName", nodeName);

	        return model;
	    }

	    public NodeService getNodeService() {
	        return nodeService;
	    }

	    public void setNodeService(NodeService nodeService) {
	        this.nodeService = nodeService;
	    }

	    public PlayedCivBean getPlayedCivBean() {
	        return playedCivBean;
	    }

	    public void setPlayedCivBean(PlayedCivBean playedCivBean) {
	        this.playedCivBean = playedCivBean;
	    }
	}


