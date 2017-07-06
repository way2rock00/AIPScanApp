package deloitte.retail.mobile.services;

import deloitte.retail.mobile.pojo.OrderHeaders;
import deloitte.retail.mobile.pojo.OrderLine;
import deloitte.retail.mobile.utility.RestURIs;
import deloitte.retail.mobile.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class OrderDetailsService {
    public OrderDetailsService() {
        super();
    }
    
    public OrderHeaders[] getOrderDetailsService(){
        String strDebug="S1:";
        List<OrderHeaders> orderHeaderList = new ArrayList<OrderHeaders>();
        List<OrderLine> orderLineList = new ArrayList<OrderLine>();
        OrderHeaders orderHeaderArray[] = null;
        String strServiceStatus = "";
        String strServiceErrMsg = "";
        resetServiceStatus();
        
        ServiceManager serviceManager = new ServiceManager();
        String selectedOrderNumber = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedOrderNum}");
        String url = RestURIs.getOrderDetailsURL(selectedOrderNumber);
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
            
            JSONObject parent = jsonObject.getJSONObject("X_PLANNED_ORDER_TAB");
            JSONArray nodeArray = parent.getJSONArray("X_PLANNED_ORDER_TAB_ITEM");

            int size = nodeArray.length();
            strDebug = strDebug +":size:"+size;
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String recordId = null;
                if (temp.getString("RECORD_ID") != null)
                    recordId = temp.getString("RECORD_ID");

                String orderId = null;
                if (temp.getString("ORDER_ID") != null)
                    orderId = temp.getString("ORDER_ID");

                String orderNumber = null;
                if (temp.getString("ORDER_NO") != null)
                    orderNumber = temp.getString("ORDER_NO");

                String orderType = null;
                if (temp.getString("TYPE") != null)
                    orderType = temp.getString("TYPE");
                
                String sourcetype = null;
                if (temp.getString("SOURCE_TYPE") != null)
                    sourcetype = temp.getString("SOURCE_TYPE");
                
                String sourceId = null;
                if (temp.getString("SOURCE_ID") != null)
                    sourceId = temp.getString("SOURCE_ID");
                
                String sourceDesc = null;
                if (temp.getString("SOURCE_DESC") != null)
                    sourceDesc = temp.getString("SOURCE_DESC");
                
                String destinationType = null;
                if (temp.getString("DESTINATION_TYPE") != null)
                    destinationType = temp.getString("DESTINATION_TYPE");
                
                String destinationId = null;
                if (temp.getString("DESTINATION_ID") != null)
                    destinationId = temp.getString("DESTINATION_ID");
                
                String destinationDesc = null;
                if (temp.getString("DESTINATION_DESC") != null)
                    destinationDesc = temp.getString("DESTINATION_DESC");
                
                String deliveryDate = null;
                if (temp.getString("DELIVERY_DATE") != null)
                    deliveryDate = temp.getString("DELIVERY_DATE");
                
                String truckId = null;
                if (temp.getString("TRUCK_ID") != null)
                    truckId = temp.getString("TRUCK_ID");
                
                String orderStatus = null;
                if (temp.getString("STATUS") != null)
                    orderStatus = temp.getString("STATUS");
                
                String releaseDate = null;
                if (temp.getString("RELEASE_DATE") != null)
                    releaseDate = temp.getString("RELEASE_DATE");
                
                String planner = null;
                if (temp.getString("PLANNER") != null)
                    planner = temp.getString("PLANNER");  
                strDebug = strDebug +":4:";
                
                JSONObject orderLines = temp.getJSONObject("ORD_LINES");
                JSONArray orderLineArray = orderLines.getJSONArray("ORD_LINES_ITEM");
                
                int orderLineSize = orderLineArray.length();
                strDebug = strDebug +":orderLineSize:"+orderLineSize;
                for (int j = 0; j < orderLineSize; j++) {
                    JSONObject orderLineTemp = orderLineArray.getJSONObject(j);
                    strDebug = strDebug +":6:";
                    String orderRecordId = null;
                    if (orderLineTemp.getString("RECORD_ID") != null)
                        orderRecordId = orderLineTemp.getString("RECORD_ID");
                    
                    String orderLineOrderId = null;
                    if (orderLineTemp.getString("ORDER_ID") != null)
                        orderLineOrderId = orderLineTemp.getString("ORDER_ID");
                    
                    String orderLineId = null;
                    if (orderLineTemp.getString("LINE_ID") != null)
                        orderLineId = orderLineTemp.getString("LINE_ID");
                    
                    String caseUPC = null;
                    if (orderLineTemp.getString("CASE_UPC") != null)
                        caseUPC = orderLineTemp.getString("CASE_UPC");
                    
                    String caseUPCDesc = null;
                    if (orderLineTemp.getString("CASE_UPC_DESC") != null)
                        caseUPCDesc = orderLineTemp.getString("CASE_UPC_DESC");
                    
                    String sellableUPC = null;
                    if (orderLineTemp.getString("SELLABLE_UPC") != null)
                        sellableUPC = orderLineTemp.getString("SELLABLE_UPC");
                    
                    String sellableUPCDesc = null;
                    if (orderLineTemp.getString("SELLABLE_UPC_DESC") != null)
                        sellableUPCDesc = orderLineTemp.getString("SELLABLE_UPC_DESC");
                    
                    String orderLineClass = null;
                    if (orderLineTemp.getString("CLASS") != null)
                        orderLineClass = orderLineTemp.getString("CLASS");
                    
                    String orderLineSubClass = null;
                    if (orderLineTemp.getString("SUBCLASS") != null)
                        orderLineSubClass = orderLineTemp.getString("SUBCLASS");
                    
                    String quantity = null;
                    if (orderLineTemp.getString("QUANTITY") != null)
                        quantity = orderLineTemp.getString("QUANTITY");    
                    
                    String uom = null;
                    if (orderLineTemp.getString("UOM") != null)
                        uom = orderLineTemp.getString("UOM");        
                    
                    OrderLine orderLine = new OrderLine(orderRecordId,orderId,orderLineId,caseUPC,caseUPCDesc
                                                        ,sellableUPC,sellableUPCDesc,orderLineClass,orderLineSubClass,quantity,uom);
                    
                    orderLineList.add(orderLine);
                    strDebug = strDebug +":7:";
                }
                strDebug = strDebug +":8:";
                OrderHeaders orderHeader = new OrderHeaders(recordId,orderId,orderNumber,orderType,sourcetype,sourceId,sourceDesc,destinationType,destinationId,destinationDesc
                                                          ,deliveryDate,truckId,orderStatus,releaseDate,planner,
                                                               orderLineList.toArray(new OrderLine[orderLineList.size()])
                                                               );           
                orderHeaderList.add(orderHeader);

            }
            strDebug = strDebug +":9:";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            strDebug = strDebug +":Error:"+e.getMessage();
        }   
        strDebug = strDebug +":10:";
        orderHeaderArray = orderHeaderList.toArray(new OrderHeaders[orderHeaderList.size()]);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", strDebug);
        return orderHeaderArray;
    }
    
    public void resetServiceStatus(){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceStatus}", "");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", "");
    }  
    
    
}
