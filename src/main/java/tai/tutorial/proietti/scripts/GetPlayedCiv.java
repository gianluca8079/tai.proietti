package tai.tutorial.proietti.scripts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import tai.tutorial.proietti.platformsample.Parametri;


public class GetPlayedCiv extends DeclarativeWebScript {

    private NodeService nodeService;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req,
            Status status) {
        String id = req.getParameter("id");
        String typeOfVictory = req.getParameter("typeOfVictory");
        String dateOfPlay = req.getParameter("dateOfPlay");

        if (id == null || typeOfVictory == null || dateOfPlay == null) {
            status.setCode(400, "Required data has not been provided");
            status.setRedirect(true);
        } else {
            NodeRef curNode = new NodeRef("workspace://SpacesStore/" + id);
            if (!nodeService.exists(curNode)) {
                status.setCode(404, "No node found for id:" + id);
                status.setRedirect(true);
            } else {
            	typeOfVictory = (String) nodeService.getProperty(curNode, Parametri.CIV_PROP_TYPE_OF_VICTORY);
            	dateOfPlay = (String) nodeService.getProperty(curNode, Parametri.CIV_PROP_DATE_OF_PLAY);
            	
            	
            }

        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("node", id);
        model.put("typeOfVictory", typeOfVictory);
        model.put("dateOfPlay", dateOfPlay);

        return model;
    }
    
    

    public NodeService getNodeService() {
        return nodeService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

   
}
