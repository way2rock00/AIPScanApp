package deloitte.retail.mobile.beans;

import deloitte.retail.mobile.utility.RestURIs;

import deloitte.retail.mobile.utility.ServiceManager;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONObject;
import deloitte.retail.mobile.services.FutureOrderPlanService;

import java.util.HashMap;

import oracle.adfmf.bindings.dbf.AmxTreeBinding;

public class BarcodeBean {
    private String barcodeError = "";
    private String barcodeResult = "";
    private String storeName="";
    private String storeId="0";
    
    private boolean validatedItem = false;
    private boolean validatedStore = false;
    
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        String strStoreName="";
        String strDebug="S:";
        try{
        HashMap hMap = (HashMap)AdfmfJavaUtilities.getELValue("#{pageFlowScope.storeNameList}");
        String strSelectedStoreid = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}");
        strStoreName = (String)hMap.get(strSelectedStoreid);
        }
        catch(Exception e){
            strStoreName="";
        }
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}",strDebug);
        return strStoreName;
    }

    public void setStoreId(String storeId) {
        String oldStoreId = this.storeId;
        this.storeId = storeId;
        propertyChangeSupport.firePropertyChange("storeId", oldStoreId, storeId);
    }

    public String getStoreId() {
        return storeId;
    }

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public PropertyChangeSupport getPropertyChangeSupport() 
    {
        return propertyChangeSupport;
    }
    
    private String barcodeFormat = "";
    private String barcodeCancelled = "";

    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport (this);

    public BarcodeBean () {
        super ();
    }

    public void scanBarcode (ActionEvent event) 
    {
        // Our AMX page includes a small JavaScript function which wraps the Cordova
        // barcode scanning function in a manner that makes it more suitable for invocation
        // from Java bean code. This is the function we are invoking below:
        System.out.println("Inside barcode bean");
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),"scanBarcodeFromJavaBean",new Object[] { });
    }

    public void setBarcodeFormat(String barcodeFormat) 
    {
        String oldBarcodeFormat = this.barcodeFormat;
        this.barcodeFormat = barcodeFormat;
        propertyChangeSupport.firePropertyChange("barcodeFormat", oldBarcodeFormat, barcodeFormat);
    }

    public String getBarcodeFormat() 
    {
        return barcodeFormat;
    }

    public void setBarcodeCancelled(String barcodeCancelled) 
    {
        String oldBarcodeCancelled = this.barcodeCancelled;
        this.barcodeCancelled = barcodeCancelled;
        propertyChangeSupport.firePropertyChange("barcodeCancelled", oldBarcodeCancelled, barcodeCancelled);
    }

    public String getBarcodeCancelled() 
    {
        return barcodeCancelled;
    }

    public void setBarcodeResult (String barcodeResult) 
    {
        String oldBarcodeResult = this.barcodeResult;
        this.barcodeResult = barcodeResult;
        propertyChangeSupport.firePropertyChange ("barcodeResult", oldBarcodeResult, barcodeResult);
        findDescription();
    }

    public String getBarcodeResult () 
    {
        return barcodeResult;
    }

    public void setBarcodeError (String barcodeError) 
    {
        String oldBarcodeError = this.barcodeError;
        this.barcodeError = barcodeError;
        propertyChangeSupport.firePropertyChange ("barcodeError", oldBarcodeError, barcodeError);
    }

    public String getBarcodeError () {
        return barcodeError;
    }

    public void addPropertyChangeListener (PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener (l);
    }

    public void removePropertyChangeListener (PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener (l);
    }
   
    public void findDescription() {
        String strDebug = "S:";
        String strItemNumber = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.BarcodeBean.barcodeResult}");
        strDebug=strDebug+"~strItemNumber:"+strItemNumber;
        
            String url = RestURIs.getScanItemDetailsURL(strItemNumber);
            strDebug = strDebug+"~"+url;
            ServiceManager serviceManager = new ServiceManager();
            String jsonArrayAsString = serviceManager.invokeREAD(url); 
            strDebug = strDebug+"~"+jsonArrayAsString;
            try{
                JSONObject jsonObject = new JSONObject(jsonArrayAsString);
                JSONObject parent = jsonObject; //jsonObject.getJSONObject("OutputParameters");
                strDebug = strDebug+"~"+"1";
                String returnStatus = null;
                if (parent.getString("X_RETURN_STATUS") != null)
                    returnStatus = parent.getString("X_RETURN_STATUS");
                strDebug = strDebug+"~"+"2:"+returnStatus;
                
                String returnMsg = null;
                if (parent.getString("X_RETURN_MSG") != null)
                    returnMsg = parent.getString("X_RETURN_MSG");
                strDebug = strDebug+"~"+"3:"+returnMsg;
                
                String desc = null;
                if (parent.getString("P_DESC") != null)
                    desc = parent.getString("P_DESC");
                strDebug = strDebug+"~"+"4:"+desc;
                String subClass = null;
                if (parent.getString("P_SUBCLASS") != null)
                    subClass = parent.getString("P_SUBCLASS");
                strDebug = strDebug+"~"+"5:"+subClass;
                String uom = null;
                if (parent.getString("P_UOM") != null)
                    uom = parent.getString("P_UOM");
                strDebug = strDebug+"~"+"5:"+uom;
                
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemNumber}",strItemNumber);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemDesc}",desc );
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemSubClass}",subClass);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemUom}",uom);

               
            }
            catch(Exception e){
                strDebug = strDebug +":Error:"+e.getMessage();
            }
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}",strDebug);
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.BarcodeBean.barcodeError}",strDebug);
    }

    public void storeValueChanged(ValueChangeEvent valueChangeEvent) {
        oracle.adfmf.bindings.dbf.AmxTreeBinding a = (oracle.adfmf.bindings.dbf.AmxTreeBinding)
            AdfmfJavaUtilities.getELValue("#{bindings.onHandQtyServiceMain}");
        a.getIterator().refresh();
    }

    public void testBtn(ActionEvent actionEvent) {
        String strTest="";
        Object o = AdfmfJavaUtilities.getELValue("#{bindings.dayWisePlanDetails}");
        strTest = strTest+":"+o.getClass();
        o = AdfmfJavaUtilities.getELValue("#{bindings.dayWisePlanDetails.collectionModel}");
        strTest = strTest+":"+o.getClass();
        
        oracle.adfmf.bindings.dbf.AmxTreeBinding a = (oracle.adfmf.bindings.dbf.AmxTreeBinding)
            AdfmfJavaUtilities.getELValue("#{bindings.dayWisePlanDetails}");
        a.getIterator().refresh();
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strTest}",strTest);
    }

    public void itemNumberChanged(ValueChangeEvent valueChangeEvent) {
       findDescription();
    }

    public void buyerSourceChange(ValueChangeEvent valueChangeEvent) {
        oracle.adfmf.bindings.dbf.AmxTreeBinding a = (oracle.adfmf.bindings.dbf.AmxTreeBinding)
            AdfmfJavaUtilities.getELValue("#{bindings.dayWisePlanDetails}");
        a.getIterator().refresh();
    }
    
    public String landingNavigation() {
        // Add event code here...
//        System.out.println("landingNavigation start");
        String pageNav=null;
       
       if (this.validatedStore) 
       {            
           if(this.validatedItem) 
           {
               pageNav = "scanProduct";
               System.out.println("validated true");
           }            
            System.out.println("validated true"); 
            //AdfmfContainerUtilities.gotoFeature("deloitte.retail.MainMenu");
//            AdfmfContainerUtilities.gotoFeature("deloitte.retail.InvTF");
        }
        else {
            pageNav = null;
        }
       
//        System.out.println("landingNavigation end");
        return pageNav;
    }   
    public void ScanIn_buttonClick(ActionEvent actionEvent) {
        //Supplier Check
//        String strReturnStatus = "E";
//        String strLoginNumber = "";
        String strErrorMessage = "";
//        String strRole="";
//        String strOprStoreId="";
        String strDebug="ScanValidation:";
        
        System.out.println("Scan_buttonClick start");
        
//        String userName = AdfmfJavaUtilities.getELValue("#{pageFlowScope.userName}").toString();
//        String password = AdfmfJavaUtilities.getELValue("#{pageFlowScope.password}").toString();

        String storeId = AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}").toString();
                    
        this.validatedStore = false;
        this.validatedItem = false;
        
        String strItemNumber = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.BarcodeBean.barcodeResult}");
        
        strDebug=strDebug+"~strItemNumber:"+strItemNumber;
        
            String url = RestURIs.getScanItemDetailsURL(strItemNumber);
            strDebug = strDebug+"~"+url;
            ServiceManager serviceManager = new ServiceManager();
            String jsonArrayAsString = serviceManager.invokeREAD(url); 
            strDebug = strDebug+"~"+jsonArrayAsString;
            try{
                JSONObject jsonObject = new JSONObject(jsonArrayAsString);
                JSONObject parent = jsonObject; //jsonObject.getJSONObject("OutputParameters");
                strDebug = strDebug+"~"+"1";
                
                String returnStatus = null;
                
                if (parent.getString("X_RETURN_STATUS") != null)
                    returnStatus = parent.getString("X_RETURN_STATUS");
                strDebug = strDebug+"~"+"2:"+returnStatus;
                
                String returnMsg = null;
                
                if (parent.getString("X_RETURN_MSG") != null)
                    returnMsg = parent.getString("X_RETURN_MSG");
                strDebug = strDebug+"~"+"3:"+returnMsg;
                
                String desc = null;
                
                if (parent.getString("P_DESC") != null)
                    desc = parent.getString("P_DESC");
                
                strDebug = strDebug+"~"+"4:"+desc;
                String subClass = null;
                
                if (parent.getString("P_SUBCLASS") != null)
                    subClass = parent.getString("P_SUBCLASS");
                
                strDebug = strDebug+"~"+"5:"+subClass;
                String uom = null;
                
                if (parent.getString("P_UOM") != null)
                    uom = parent.getString("P_UOM");
                strDebug = strDebug+"~"+"5:"+uom;
                
//                if(returnStatus==null || "".equals(returnStatus))
                if("S".equals(returnStatus))
                {         
                    this.validatedItem=true;
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemNumber}",strItemNumber);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemDesc}",desc );
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemSubClass}",subClass);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.itemUom}",uom);
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItmScanMessage}", "");
                }
                else 
                {
                    this.validatedItem=false;
                    AdfmfJavaUtilities.setELValue("#{pageFlowScope.ItmScanMessage}", "Please scan an valid Item.");
                }
            }
            catch(Exception e){
                strDebug = strDebug +":Error:"+e.getMessage();
            }
            
//            AdfmfJavaUtilities.setELValue("#{pageFlowScope.BarcodeBean.barcodeError}",strDebug);
    //        strReturnStatus = "S";
        if("-999".equals(storeId) || (storeId==null || ("".equals(storeId)))) 
        {
            this.validatedStore=false;   
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.StrScanMessage}", "Please select a store Id.");  
        }
        else 
        {
           this.validatedStore=true;    
           AdfmfJavaUtilities.setELValue("#{pageFlowScope.StrScanMessage}", " ");  
        }
//        if("E".equalsIgnoreCase(strReturnStatus)){
//            this.validated = false;
//            AdfmfJavaUtilities.setELValue("#{pageFlowScope.ScanMessage}", "Invalid Credentials Provided.");
//        }
//        else
//        {
//            this.validated = true;
//            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", null);
//            AdfmfJavaUtilities.setELValue("#{applicationScope.loggedInUser}", userName);
//            AdfmfJavaUtilities.setELValue("#{applicationScope.loggedInBuyerNumber}", strLoginNumber);
//            AdfmfJavaUtilities.setELValue("#{pageFlowScope.showSearchRegion}", "false");
//            AdfmfJavaUtilities.setELValue("#{applicationScope.loginRole}", strRole);
//            AdfmfJavaUtilities.setELValue("#{applicationScope.oprStrId}", strOprStoreId);
//        }
//        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", strDebug);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}",strDebug);
        System.out.println("Scan_buttonClick end");
    }
  
}
