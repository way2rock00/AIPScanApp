package deloitte.retail.mobile.services;

import deloitte.retail.mobile.pojo.Location;
import deloitte.retail.mobile.pojo.PlannedOrderSummaryInfo;

import deloitte.retail.mobile.utility.RestURIs;
import deloitte.retail.mobile.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class PlannedOrderSummaryService {
    public PlannedOrderSummaryService() {
        super();
    }
    
    private List<PlannedOrderSummaryInfo> plannedOrderSummaryList  = new ArrayList<PlannedOrderSummaryInfo>();


    public PlannedOrderSummaryInfo[] getPlannedOrderSummary() {
        PlannedOrderSummaryInfo[] plannedOrderArray = null;
        plannedOrderSummaryList = new ArrayList<PlannedOrderSummaryInfo>();
        ServiceManager serviceManager = new ServiceManager();
        String buyer = (String)AdfmfJavaUtilities.getELValue("#{applicationScope.loggedInBuyerNumber}");
        String strDebug = "plannedOrder:"+buyer;
        System.out.println("buyer");
        String url = RestURIs.getPlannedSummaryURL(buyer); 
        strDebug = strDebug +"::"+url;
        String strServiceStatus = "";
        String strServiceErrMsg = "";
        resetServiceStatus();
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        strDebug = strDebug + ":"+jsonArrayAsString;
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            if (jsonObject.getString("X_RETURN_STATUS") != null)
                strServiceStatus = jsonObject.getString("X_RETURN_STATUS");
            
            if (jsonObject.getString("X_RETURN_MSG") != null)
                strServiceErrMsg = jsonObject.getString("X_RETURN_MSG");            
            strDebug = strDebug + ":strServiceStatus:"+strServiceStatus+":strServiceErrMsg:"+strServiceErrMsg;
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", strServiceErrMsg);
            JSONObject parent = jsonObject.getJSONObject("P_PLANNED_ORD_SUMMARY_TAB");
            JSONArray nodeArray = parent.getJSONArray("P_PLANNED_ORD_SUMMARY_TAB_ITEM");

            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String recordId = null;
                if (temp.getString("RECORD_ID") != null)
                    recordId = temp.getString("RECORD_ID");

                String status = null;
                if (temp.getString("STATUS") != null)
                    status = temp.getString("STATUS");

                String status_count = null;
                if (temp.getString("STATUS_COUNT") != null)
                    status_count = temp.getString("STATUS_COUNT");

                PlannedOrderSummaryInfo plannedOrderSummaryRec = new PlannedOrderSummaryInfo(recordId, status, status_count);

                plannedOrderSummaryList.add(plannedOrderSummaryRec);

                System.out.println("summary servie:" + status);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            strDebug = strDebug + ":Error:"+e.getMessage();
        }     
        plannedOrderArray = plannedOrderSummaryList.toArray(new PlannedOrderSummaryInfo[plannedOrderSummaryList.size()]);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchFieldsValid}","true");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.deliveryFromDateError}","false");     
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.deliveryToDateError}","false");     
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.fetchSizeError}","false");    
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", strDebug);
        return plannedOrderArray;
    }
    
    public Location[] getSourceLocation(){
        List<Location> locationList = new ArrayList<Location>();
        Location locationArray[]= null;
        ServiceManager serviceManager = new ServiceManager();
        String url = RestURIs.getLocationURL("SOURCE");
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
           JSONArray nodeArray = jsonObject.getJSONArray("dbGetPlanOrderSourceOutput");

            Location locationRec =  new Location ("-1", "-999", "Select Location");
            locationList.add(locationRec);
            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String locationId = null;
                if (temp.getString("source_id") != null)
                    locationId = temp.getString("source_id");

                String locationDesc = null;
                if (temp.getString("source_desc") != null)
                    locationDesc = temp.getString("source_desc");

                locationRec = new Location (locationId,locationDesc, locationDesc);

                locationList.add(locationRec);


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }     
        locationArray = locationList.toArray(new Location[locationList.size()]);
        return locationArray;

    }
    
    public Location[] getDestinationLocation(){
        String strDestDebug = "S";
        List<Location> locationList = new ArrayList<Location>();
        Location locationArray[]= null;
        ServiceManager serviceManager = new ServiceManager();
        String url = RestURIs.getLocationURL("DESTINATION");
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        strDestDebug = strDestDebug +":"+jsonArrayAsString;
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
           JSONArray nodeArray = jsonObject.getJSONArray("dbGetPlanOrdDestinationOutput");

            int size = nodeArray.length();
            Location locationRec = new Location ("-1", "-999", "Select Location");
            locationList.add(locationRec);
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String locationId = null;
                if (temp.getString("destination_id") != null)
                    locationId = temp.getString("destination_id");

                String locationDesc = null;
                if (temp.getString("destination_desc") != null)
                    locationDesc = temp.getString("destination_desc");

                locationRec = new Location (locationId,locationDesc, locationDesc);

                locationList.add(locationRec);


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            strDestDebug = strDestDebug +":"+e.getMessage();
        }     
        locationArray = locationList.toArray(new Location[locationList.size()]);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDestDebug}", strDestDebug);
        return locationArray;

    }    
    
    public void resetServiceStatus(){
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceStatus}", "");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", "");
    }
}
