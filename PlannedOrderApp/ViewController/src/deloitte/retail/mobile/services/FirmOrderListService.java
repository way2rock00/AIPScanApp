 package deloitte.retail.mobile.services;

import deloitte.retail.mobile.pojo.FirmOrder;
//import deloitte.retail.mobile.pojo.OrderHeaders;
import deloitte.retail.mobile.utility.RestURIs;
import deloitte.retail.mobile.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

//import java.util.Map;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class FirmOrderListService {
    public FirmOrderListService() {
        super();
    }
    
    public FirmOrder[] getFirmOrderList()
    {
        String strDebug="S1:";
        List<FirmOrder> firmOrderList = new ArrayList<FirmOrder>();
        FirmOrder firmOrderArray[] = null;
        String strServiceStatus = "";
        String strServiceErrMsg = "";
        resetServiceStatus();
        
        ServiceManager serviceManager = new ServiceManager();
        
        String strSellableUpc = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.itemNumber}");
        String strStoreId = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}");
        String url = RestURIs.getFirmOrderURL(strSellableUpc, strStoreId);
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        try 
        {
            strDebug = strDebug +":2:";
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            
            if (jsonObject.getString("X_RETURN_STATUS") != null)
                strServiceStatus = jsonObject.getString("X_RETURN_STATUS");
            
            if (jsonObject.getString("X_RETURN_MSG") != null)
                strServiceErrMsg = jsonObject.getString("X_RETURN_MSG");            
            strDebug = strDebug + ":strServiceStatus:"+strServiceStatus+":strServiceErrMsg:"+strServiceErrMsg;
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", strServiceErrMsg);
            
            JSONObject parent = jsonObject.getJSONObject("P_FO_QTY_TAB");
            JSONArray nodeArray = parent.getJSONArray("P_FO_QTY_TAB_ITEM");
            
            int size = nodeArray.length();
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.NodeArraySize}", ""+size);
            strDebug = strDebug +":size:"+size;
            for (int i = 0; i < size; i++) 
            {
                strDebug = strDebug + " i: "+i;
                JSONObject temp = nodeArray.getJSONObject(i);
                
                String odrNumber = null;
                if (temp.getString("ORDER_NUMBER") != null)
                    odrNumber = temp.getString("ORDER_NUMBER");
                strDebug = strDebug + " odrNumber: "+odrNumber;
                String deliveryDate = null;
                if (temp.getString("DELIVERY_DATE") != null)
                    deliveryDate = temp.getString("DELIVERY_DATE");
                strDebug = strDebug + " deliveryDate: "+deliveryDate;
                String source = null;
                if (temp.getString("SOURCE") != null)
                    source = temp.getString("SOURCE");
                strDebug = strDebug + " source: "+source;
                String sourceType = null;
                if (temp.getString("SOURCE_TYPE") != null)
                    sourceType = temp.getString("SOURCE_TYPE");
                strDebug = strDebug + " sourceType: "+sourceType;
                String quantity = null;
                if (temp.getString("QUANTITY") != null)
                    quantity = temp.getString("QUANTITY");
                strDebug = strDebug + " quantity: "+quantity;
                String packSize = null;
                if (temp.getString("PACK_SIZE") != null)
                    packSize = temp.getString("PACK_SIZE");
                strDebug = strDebug + " packSize: "+packSize;
                String buyer = null;
                if (temp.getString("BUYER") != null)
                    buyer = temp.getString("BUYER");
                strDebug = strDebug + " buyer: "+buyer;
                String price = null;
                if (temp.getString("PRICE") != null)
                    price = temp.getString("PRICE");
                strDebug = strDebug + " price: "+price;
            strDebug = strDebug +":8:";
            FirmOrder firmOrder = new FirmOrder( odrNumber
                                                ,deliveryDate
                                                ,quantity
                                                ,packSize
                                                ,source
                                                ,sourceType
                                                ,buyer
                                                ,price);
            firmOrderList.add(firmOrder);
            }
            strDebug = strDebug +":9:";
        } 
        
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            strDebug = strDebug +":Error:"+e.getMessage();
        }
        strDebug = strDebug +":10:";
        firmOrderArray = firmOrderList.toArray(new FirmOrder[firmOrderList.size()]);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", strDebug);
        return firmOrderArray;
    }
    
    public void resetServiceStatus(){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceStatus}", "");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", ""); 
    } 
}
