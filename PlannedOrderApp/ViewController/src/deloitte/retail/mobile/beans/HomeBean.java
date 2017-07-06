package deloitte.retail.mobile.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxBindingContainer;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class HomeBean {
    public HomeBean() {
        super();
    }

    public void springBoardEvent(ActionEvent actionEvent) {
        // Add event code here...
        String strDebug = "springBoardEvent Start:";
        try{
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.showSearchRegion}","false");            
        AdfmfContainerUtilities.gotoSpringboard();
        }
        catch(Exception e){
            strDebug = strDebug + ":"+e.getMessage();
        }
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", strDebug);
        
    }
    
    public void resetSearchFields(ActionEvent actionEvent){
        resetSearchFields();
    }
    public void resetSearchFields(){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchOrderType}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchStatus}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchSource}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchDestination}", null);
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchDeliveryFromDate}", null);
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchDeliveryToDate}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchDeliveryFromDate}", getPreferenceDeliveryStartDate());
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchDeliveryToDate}", getPreferenceDeliveryEndDate());
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchOrderNumberFrom}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchOrderNumberTo}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchCaseUPCFrom}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchCaseUPCTo}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchTruckNumber}", null);  
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchFieldsValid}","true");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.deliveryFromDateError}","false");     
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.deliveryToDateError}","false");     
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.fetchSizeError}","false");     
    }

    public void dateChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", valueChangeEvent.getNewValue()+"~"+valueChangeEvent.getOldValue());
    }

    public void searchPlannedOrders(ActionEvent actionEvent) {
        String strDeliveryDateFrom = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchDeliveryFromDate}");
        String strDeliveryDateTo = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchDeliveryToDate}");
        String strFetchSize= (String)AdfmfJavaUtilities.getELValue("#{preferenceScope.application.UserSettings.RecordSize}");
        String isError = "N";
        if(strDeliveryDateFrom == null || "".equalsIgnoreCase(strDeliveryDateFrom)){
            isError = "Y";
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.deliveryFromDateError}","true");
        }else{
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.deliveryFromDateError}","false"); 
        }
        
        if(strDeliveryDateTo == null || "".equalsIgnoreCase(strDeliveryDateTo)){
            isError = "Y";
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.deliveryToDateError}","true");
        }else{
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.deliveryToDateError}","false"); 
        }
        if(strFetchSize == null || "".equalsIgnoreCase(strFetchSize)){
            isError = "Y";
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.fetchSizeError}","true");
        }else{
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.fetchSizeError}","false"); 
        }      
        
        if("Y".equalsIgnoreCase(isError)){
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchFieldsValid}","false");
        }
        else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchFieldsValid}","true");
        }
        
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDateDebug}",""+AdfmfJavaUtilities.getELValue("#{preferenceScope.application.UserSettings.DeliveryMonths}")
                                      +":"+AdfmfJavaUtilities.getELValue("#{preferenceScope.application.UserSettings.RecordSize}")
                                      );
        
    }

    public String searchPlannedOrderClicked() {
        String strSearchFieldsValid = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchFieldsValid}");
        String strRetValue="";
        if("true".equalsIgnoreCase(strSearchFieldsValid)){
            strRetValue = "toOrderHeaderList";
        } 
        return strRetValue;
    }

    public String backToSummary() {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.showSearchRegion}","false");
        return "backToSummary";
    }


    public void savePreference(ActionEvent actionEvent) {
//        AdfmfJavaUtilities.setELValue("#{preferenceScope.application.UserSettings.DeliveryMonths}",AdfmfJavaUtilities.getELValue("#{pageFlowScope.prefDeliveryMonth}"));
//        AdfmfJavaUtilities.setELValue("#{preferenceScope.application.UserSettings.RecordSize}",AdfmfJavaUtilities.getELValue("#{pageFlowScope.prefRecordLimit}"));
        AdfmfContainerUtilities.gotoSpringboard();
    }
    
    public String getPreferenceDeliveryStartDate(){
        String strDate = "";
        try{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate cal = LocalDate.now();
        strDate = dtf.format(cal);
        }
        catch(Exception e){
        }
        return strDate;
    }
    public String getPreferenceDeliveryEndDate(){
        String strDate = "";
        String strDateDebug = "";
       try{
           long weeks = Long.valueOf(AdfmfJavaUtilities.getELValue("#{preferenceScope.application.UserSettings.DeliveryMonths}")==null?"0":
                                        (String)AdfmfJavaUtilities.getELValue("#{preferenceScope.application.UserSettings.DeliveryMonths}")
                                   );
            strDateDebug = strDateDebug + ":"+weeks;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate cal = LocalDate.now();
          cal = cal.plusWeeks(weeks);
            strDateDebug = strDateDebug +":MonthAd";
        strDate = dtf.format(cal);
            strDateDebug = strDateDebug +":FInish";
        }
        catch(Exception e){
            strDateDebug = strDateDebug + ":"+e.getMessage();
        }
        strDateDebug = strDateDebug +":End";
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDateDebug}",strDateDebug);
        return strDate;
    }

//    public void setPreValues() {
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.prefDeliveryMonth}",AdfmfJavaUtilities.getELValue("#{preferenceScope.application.UserSettings.DeliveryMonths}"));
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.prefRecordLimit}",AdfmfJavaUtilities.getELValue("#{preferenceScope.application.UserSettings.RecordSize}"));
//    }
    public void navigateToInventoryTF(ActionEvent actionEvent) {
       AdfmfContainerUtilities.gotoFeature("deloitte.retail.InvTF");
    }
    
    public void navigateToPlannedOrderTF(ActionEvent actionEvent) {
       AdfmfContainerUtilities.gotoFeature("deloitte.retail.Home");
    }
}
