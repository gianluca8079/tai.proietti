package tai.tutorial.proietti.scripts;

import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;

import tai.tutorial.proietti.beans.DeletePlayedCivBean;

import org.springframework.extensions.webscripts.Status;


public class DeletePlayedCiv extends DeclarativeWebScript {
    private NodeService nodeService;
    DeletePlayedCivBean deletePlayedCivBean;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req,
            Status status) {
        String id = req.getParameter("id");
        String civilizationName = req.getParameter("civilizationName");

        if (id == null ||  civilizationName == null) {
            status.setCode(400, "Required data has not been provided");
            status.setRedirect(true);
        } else {
            NodeRef curNode = new NodeRef("workspace://SpacesStore/" + id);
            if (!nodeService.exists(curNode)) {
                status.setCode(404, "No node found for id:" + id);
                status.setRedirect(true);
            } else {
				deletePlayedCivBean.delete(curNode, civilizationName);
            }

        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("node", id);
        model.put("civilizationName", civilizationName);

        return model;
    }

    public NodeService getNodeService() {
        return nodeService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }
    public DeletePlayedCivBean getDeletePlayedCivBean() {
        return deletePlayedCivBean;
    }

    public void setDeletePlayedCivBean(DeletePlayedCivBean DeletePlayedCivBean) {
        this.deletePlayedCivBean = DeletePlayedCivBean;
    }

    

}
