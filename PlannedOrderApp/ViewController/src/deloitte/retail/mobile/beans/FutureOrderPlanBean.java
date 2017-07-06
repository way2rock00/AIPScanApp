package deloitte.retail.mobile.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxBindingContainer;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class FutureOrderPlanBean {
    public FutureOrderPlanBean() {
    }

    public void setSeq(ActionEvent actionEvent) 
    {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.futurePlanSeq", "0");    
    }

    public void setBuyer(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{bindings.source1.inputValue}", "7655");
    }
}
