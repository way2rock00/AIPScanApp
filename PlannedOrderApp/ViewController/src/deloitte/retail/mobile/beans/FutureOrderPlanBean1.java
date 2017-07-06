package deloitte.retail.mobile.beans;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class FutureOrderPlanBean1 {
    public FutureOrderPlanBean1() {
    }

    public void setSeq(ActionEvent actionEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.futurePlanSeq", "0");    
    }
}
