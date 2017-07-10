package deloitte.retail.mobile.beans;

import deloitte.retail.mobile.utility.RestURIs;

import deloitte.retail.mobile.utility.ServiceManager;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONObject;

public class PlannedOrderBean {
    public PlannedOrderBean() {
        super();
    }
    private boolean validated = false;
    
    public String landingNavigation() {
        // Add event code here...
        System.out.println("landingNavigation start");
        String pageNav;
       
       if (this.validated) {
            
            pageNav = "loginSuccess";
            System.out.println("validated true");
            //AdfmfContainerUtilities.gotoFeature("deloitte.retail.MainMenu");
            AdfmfContainerUtilities.gotoFeature("deloitte.retail.InvTF");
        }
        else {
            pageNav = null;
        }
        System.out.println("landingNavigation end");
        return pageNav;
    }
    
    public void LoginIn_buttonClick(ActionEvent actionEvent) {
        //Supplier Check
        String strReturnStatus = "E";
        String strLoginNumber = "";
        String strErrorMessage = "";
        String strRole="";
        String strOprStoreId="";
        
        
        System.out.println("LoginIn_buttonClick start");
        String userName = AdfmfJavaUtilities.getELValue("#{pageFlowScope.userName}").toString();
        String password = AdfmfJavaUtilities.getELValue("#{pageFlowScope.password}").toString();
        System.out.println("user:" + userName);
        System.out.println("password:" + password);
        this.validated = false;
        String url = RestURIs.getLoginURL(userName, password,"23131313131" , "BUYER","app.plannedOrder" );
        String strDebug = "s1:"+url;

        ServiceManager serviceManager = new ServiceManager();
        System.out.println("po status by header url:" + url);
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        strDebug = strDebug +"s2:"+jsonArrayAsString;

            try{
                JSONObject jsonObject = new JSONObject(jsonArrayAsString);
                if(jsonObject != null){
                    
                    if(jsonObject.getString("X_RETURN_STATUS") != null){
                        strReturnStatus = jsonObject.getString("X_RETURN_STATUS");
                    }
                    if(jsonObject.getString("X_ENTITY_NUM") != null){
                        strLoginNumber = jsonObject.getString("X_ENTITY_NUM");
                    }
                    if(jsonObject.getString("X_RETURN_MSG") != null || !"VALID".equals(jsonObject.getString("X_RETURN_MSG")))
                    {
                        strErrorMessage = jsonObject.getString("X_RETURN_MSG");
                    }
                    if(jsonObject.getString("X_ROLE") != null)
                    {
                        strRole = jsonObject.getString("X_ROLE");
                    }
                    if(jsonObject.getString("X_STORE_ID") != null)
                    {
                        strOprStoreId = jsonObject.getString("X_STORE_ID");
                    }
                    
                    
                }
            }
            catch(Exception e){
                strDebug = strDebug +"Exception:"+e.getMessage();
            }
        strDebug = strDebug + ":s3:" + strReturnStatus + ":s3:" + strLoginNumber;
//        strReturnStatus = "S";
        if("E".equalsIgnoreCase(strReturnStatus)){
            this.validated = false;
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", "Invalid Credentials Provided.");
        }
        else
        {
            this.validated = true;
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", null);
            AdfmfJavaUtilities.setELValue("#{applicationScope.loggedInUser}", userName);
            AdfmfJavaUtilities.setELValue("#{applicationScope.loggedInBuyerNumber}", strLoginNumber);
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.showSearchRegion}", "false");
            AdfmfJavaUtilities.setELValue("#{applicationScope.loginRole}", strRole);
            AdfmfJavaUtilities.setELValue("#{applicationScope.oprStrId}", strOprStoreId);
        }
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", strDebug);
        System.out.println("LoginIn_buttonClick end");
    }   
}
