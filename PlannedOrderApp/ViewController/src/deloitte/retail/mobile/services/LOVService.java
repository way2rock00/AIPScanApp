package deloitte.retail.mobile.services;


import deloitte.retail.mobile.pojo.Buyer;
import deloitte.retail.mobile.pojo.PlanOrderInvDetails;
import deloitte.retail.mobile.pojo.Source;
import deloitte.retail.mobile.pojo.Store;
import deloitte.retail.mobile.utility.RestURIs;
import deloitte.retail.mobile.utility.ServiceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class LOVService {
    public LOVService() {
        super();
    }
    public Buyer[] getBuyerLOV(){
        String strBuyerDebug="Buyer:";
        List<Buyer> buyerList = new ArrayList<Buyer>();
        Buyer buyerArray[] = null;
        
        ServiceManager serviceManager = new ServiceManager();
        String selectedStoreId = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}");
        String sellableUPC  = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.itemNumber}");
                
        strBuyerDebug=strBuyerDebug+"Selcted Store Id:"+(String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}");
        strBuyerDebug=strBuyerDebug+"Item:"+(String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.itemNumber}");
        
        String url = RestURIs.getBuyerLOVURI(selectedStoreId,sellableUPC);
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        strBuyerDebug=strBuyerDebug+jsonArrayAsString;
        try {
            strBuyerDebug = strBuyerDebug +":2:";
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);       
            
            JSONArray nodeArray = jsonObject.getJSONArray("dbGetBuyerDetailsOutput");
            Buyer buyerObj = new Buyer("-999","Select Buyer");
            
            buyerList.add(buyerObj);
            int size = nodeArray.length();
            strBuyerDebug = strBuyerDebug +":size:"+size;
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);
                
                String buyerId = null;
                if (temp.getString("buyer_id") != null)
                    buyerId = temp.getString("buyer_id");                

                String buyer = null;
                if (temp.getString("buyer_name") != null)
                    buyer = temp.getString("buyer_name");

                buyerObj = new Buyer(buyerId,buyer);
                buyerList.add(buyerObj);

            }
            strBuyerDebug = strBuyerDebug +":9:";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            strBuyerDebug = strBuyerDebug +":Error:"+e.getMessage();
        }   
        strBuyerDebug = strBuyerDebug +":10:";
        buyerArray = buyerList.toArray(new Buyer[buyerList.size()]);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strBuyerDebug}", strBuyerDebug);
        return buyerArray;
    }    
    
    public Source[] getSourceLOV(){
        String strSourceDebug="SourceDebug:";
        List<Source> sourceList = new ArrayList<Source>();
        Source sourceArray[] = null;
        
        ServiceManager serviceManager = new ServiceManager();
        String selectedStoreId = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}");
        String sellableUPC  = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.itemNumber}");
        
        
        String url = RestURIs.getSourceLOVURI(selectedStoreId,sellableUPC);
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        strSourceDebug=strSourceDebug+jsonArrayAsString;
        
        try {
            strSourceDebug = strSourceDebug +":2:";
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);       
            
            JSONArray nodeArray = jsonObject.getJSONArray("dbGetAIPSourceDetailsOutput");
            Source sourceObj = new Source("-999","Select Source");
            sourceList.add(sourceObj);
            int size = nodeArray.length();
            strSourceDebug = strSourceDebug +":size:"+size;
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String sourceId = null;
                if (temp.getString("source_id") != null)
                    sourceId = temp.getString("source_id");
                
                String source = null;
                if (temp.getString("source_name") != null)
                    source = temp.getString("source_name");                

                sourceObj = new Source(sourceId,source);
               sourceList.add(sourceObj);

            }
            strSourceDebug = strSourceDebug +":9:";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            strSourceDebug = strSourceDebug +":Error:"+e.getMessage();
        }   
        strSourceDebug = strSourceDebug +":10:";
        sourceArray = sourceList.toArray(new Source[sourceList.size()]);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strSourceDebug}", strSourceDebug);
        return sourceArray;
    }
    
    public Store[] getStoreLOV(){
        String strStoreDebug="S1:";
        List<Store> storeList = new ArrayList<Store>();
        HashMap storeNames = new HashMap();
        Store storeArray[] = null;
        
        ServiceManager serviceManager = new ServiceManager();
//        String selectedStoreId = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}");
        String selectedStoreId = "1";
        
        String url = RestURIs.getStoreLOVURI(selectedStoreId);
        strStoreDebug = strStoreDebug +":url:"+url;
        String strServiceStatus = "";
        String strServiceErrMsg = "";
        resetServiceStatus();
        
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        try {
            strStoreDebug = strStoreDebug +":2:";
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);      
            
            if (jsonObject.getString("X_RETURN_STATUS") != null)
                strServiceStatus = jsonObject.getString("X_RETURN_STATUS");
            
            if (jsonObject.getString("X_RETURN_MSG") != null)
                strServiceErrMsg = jsonObject.getString("X_RETURN_MSG"); 
            
            JSONObject parent = jsonObject.getJSONObject("P_STORE_TAB");
            JSONArray nodeArray = parent.getJSONArray("P_STORE_TAB_ITEM");
            
            Store storeObj = new Store("-999","Select Store");
            storeList.add(storeObj);
            int size = nodeArray.length();
            strStoreDebug = strStoreDebug +":size:"+size;
            
            for (int i = 0; i < size; i++) 
            {
                JSONObject temp = nodeArray.getJSONObject(i);

                String storeId = null;
                if (temp.getString("STORE_ID") != null)
                    storeId = temp.getString("STORE_ID");
                
                String store = null;
                if (temp.getString("STORE_NAME") != null)
                    store = temp.getString("STORE_NAME");                

               storeObj = new Store(storeId,store);
                strStoreDebug = strStoreDebug +":8.1:storeId:"+storeId+" store:"+store;
               storeList.add(storeObj);
                storeNames.put(storeId, store);
            }
            strStoreDebug = strStoreDebug +":9:";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            strStoreDebug = strStoreDebug +":Error:"+e.getMessage();        }   
        strStoreDebug = strStoreDebug +":10:";
        storeArray = storeList.toArray(new Store[storeList.size()]);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.storeNameList}", storeNames);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strSourceDebug}", strStoreDebug);
        
        //Default Selected Store Id in Store LOV.
        String strSelectedStoreId = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}");
        if(strSelectedStoreId==null||"".equalsIgnoreCase(strSelectedStoreId)){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.selectedStoreId}", 
                                      (String)AdfmfJavaUtilities.getELValue("#{applicationScope.oprStrId}"));
        }
        return storeArray;
    }
    public void resetServiceStatus()
    {
       AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceStatus}", "");
       AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", "");
    }
}
