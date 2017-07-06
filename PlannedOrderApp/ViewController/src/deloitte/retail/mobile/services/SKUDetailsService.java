package deloitte.retail.mobile.services;


import deloitte.retail.mobile.pojo.SKUDetails;
import deloitte.retail.mobile.utility.RestURIs;
import deloitte.retail.mobile.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class SKUDetailsService {
    public SKUDetailsService() {
        super();
    }
    
    public SKUDetails[] getSkuDetailsService(){
        String strDebug="S1:";
        List<SKUDetails> skuDetailsList = new ArrayList<SKUDetails>();
        SKUDetails skuDetailsArray[] = null;
        String strServiceStatus = "";
        String strServiceErrMsg = "";
        
        ServiceManager serviceManager = new ServiceManager();
        String selSKUCaseUPC = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selSKUCaseUPC}");
        String selSKUSellableUPC = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selSKUSellableUPC}");
        String selSKUDeliveryDate = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selSKUDeliveryDate}");
        String selSKUStoreId = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selSKUStoreId}");
        selSKUStoreId = "3211";//temp
        String url = RestURIs.getSKUDetailsURL(selSKUCaseUPC, selSKUSellableUPC, selSKUDeliveryDate, selSKUStoreId);
        strDebug = strDebug +":url:"+url;
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        try {
            strDebug = strDebug +":2:";
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            if (jsonObject.getString("X_RETURN_STATUS") != null)
                strServiceStatus = jsonObject.getString("X_RETURN_STATUS");
            
            if (jsonObject.getString("X_RETURN_MSG") != null)
                strServiceErrMsg = jsonObject.getString("X_RETURN_MSG");            
            strDebug = strDebug + ":strServiceStatus:"+strServiceStatus+":strServiceErrMsg:"+strServiceErrMsg;
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", strServiceErrMsg);
            
            JSONObject parent = jsonObject.getJSONObject("P_STR_DAY_SKU_TAB");
            JSONArray nodeArray = parent.getJSONArray("P_STR_DAY_SKU_TAB_ITEM");

            int size = nodeArray.length();
            strDebug = strDebug +":size:"+size;
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String recordId = null;
                if (temp.getString("RECORD_ID") != null)
                    recordId = temp.getString("RECORD_ID");

                String caseUPC = null;
                if (temp.getString("CASE_UPC") != null)
                    caseUPC = temp.getString("CASE_UPC");

                String deliveryDate = null;
                if (temp.getString("DELIVERY_DATE") != null)
                    deliveryDate = temp.getString("DELIVERY_DATE");

                String onHandQty = null;
                if (temp.getString("ON_HAND_QTY") != null)
                    onHandQty = temp.getString("ON_HAND_QTY");
                
                String onOrderQty = null;
                if (temp.getString("ON_ORDER_QTY") != null)
                    onOrderQty = temp.getString("ON_ORDER_QTY");
                
                String allocatedQty = null;
                if (temp.getString("ALLOCATED_QTY") != null)
                    allocatedQty = temp.getString("ALLOCATED_QTY");
                
                String salesForeCastQty = null;
                if (temp.getString("SALES_FORECAST_QTY") != null)
                    salesForeCastQty = temp.getString("SALES_FORECAST_QTY");
                
                String safetyStockQty = null;
                if (temp.getString("SAFETY_STOCK_QTY") != null)
                    safetyStockQty = temp.getString("SAFETY_STOCK_QTY");
                
                String packSize = null;
                if (temp.getString("PACK_SIZE") != null)
                    packSize = temp.getString("PACK_SIZE");
                
                SKUDetails skuDetails = new SKUDetails(recordId,caseUPC,deliveryDate,onHandQty,onOrderQty,allocatedQty,salesForeCastQty,safetyStockQty,packSize);          
                skuDetailsList.add(skuDetails);

            }
            strDebug = strDebug +":9:";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            strDebug = strDebug +":Error:"+e.getMessage();
        }   
        strDebug = strDebug +":10:";
        skuDetailsArray = skuDetailsList.toArray(new SKUDetails[skuDetailsList.size()]);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", strDebug);
        return skuDetailsArray;
    }    
}
