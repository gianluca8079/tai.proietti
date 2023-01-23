package tai.tutorial.proietti.scripts;

import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import tai.tutorial.proietti.beans.AddVictoryPlayedCivBean;


public class AddVictoryPlayedCiv extends DeclarativeWebScript {

    private AddVictoryPlayedCivBean AddVictoryPlayedCivBean;
    private NodeService nodeService;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req,
            Status status) {
        String id = req.getParameter("id");
        

        if (id == null) {
            status.setCode(400, "Required data has not been provided");
            status.setRedirect(true);
        } else {
            NodeRef curNode = new NodeRef("workspace://SpacesStore/" + id);
            if (!nodeService.exists(curNode)) {
                status.setCode(404, "No node found for id:" + id);
                status.setRedirect(true);
            } else {
                AddVictoryPlayedCivBean.addAspect(curNode);
            }

        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("node", id);
        
        return model;
    }

   

	public NodeService getNodeService() {
        return nodeService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public AddVictoryPlayedCivBean getAddVictoryPlayedCivBean() {
        return AddVictoryPlayedCivBean;
    }

    public void setAddVictoryPlayedCivBean(AddVictoryPlayedCivBean AddVictoryPlayedCivBean) {
        this.AddVictoryPlayedCivBean = AddVictoryPlayedCivBean;
    }
}
