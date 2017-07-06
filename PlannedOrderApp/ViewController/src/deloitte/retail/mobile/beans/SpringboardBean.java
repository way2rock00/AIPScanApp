package deloitte.retail.mobile.beans;


import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class SpringboardBean {
    public SpringboardBean() {
        super();
    }


    public void springboardClicked(ActionEvent actionEvent) {
       String strFeatureName = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedFeatureName}");
        String strFeatureId = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedFeatureId}");
       
       if("Logout".equalsIgnoreCase(strFeatureName)){
           AdfmfJavaUtilities.logout();
           AdfmfContainerUtilities.gotoFeature("deloitte.retail.Login");
       }else{
           AdfmfContainerUtilities.gotoFeature(strFeatureId);
       }
    }
}
