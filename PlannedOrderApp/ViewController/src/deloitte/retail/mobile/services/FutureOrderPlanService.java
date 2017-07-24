package deloitte.retail.mobile.services;

import deloitte.retail.mobile.pojo.FutureOrderPlan;

import deloitte.retail.mobile.pojo.OnHandQty;

import deloitte.retail.mobile.pojo.PlanOrderInvDetails;
import deloitte.retail.mobile.utility.RestURIs;
import deloitte.retail.mobile.utility.ServiceManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;


import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class FutureOrderPlanService {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    private FutureOrderPlan[] futureOrderPlanDetails;
    private PlanOrderInvDetails[] dayWisePlanDetails;


    public void setFutureOrderPlanDetails(FutureOrderPlan[] futureOrderPlanDetails) {
        FutureOrderPlan[] oldFutureOrderPlanDetails = this.futureOrderPlanDetails;
        this.futureOrderPlanDetails = futureOrderPlanDetails;
        propertyChangeSupport.firePropertyChange("futureOrderPlanDetails", oldFutureOrderPlanDetails,
                                                 futureOrderPlanDetails);
    }

    public FutureOrderPlan[] getFutureOrderPlanDetails() {
        futureOrderPlanDetails=getFutureOrderPlanService();
        return futureOrderPlanDetails;//futureOrderPlanDetails;
    }

    public void setDayWisePlanDetails(PlanOrderInvDetails[] dayWisePlanDetails) {
        PlanOrderInvDetails[] oldDayWisePlanDetails = this.dayWisePlanDetails;
        this.dayWisePlanDetails = dayWisePlanDetails;
        propertyChangeSupport.firePropertyChange("dayWisePlanDetails", oldDayWisePlanDetails, dayWisePlanDetails);
    }

    public PlanOrderInvDetails[] getDayWisePlanDetails() {
        dayWisePlanDetails =getDayDetails();
        return dayWisePlanDetails;//dayWisePlanDetails;
    }

    public FutureOrderPlanService() {
        super();
    }
    private List<PlanOrderInvDetails> planOrderInvDetailsList  = new ArrayList<PlanOrderInvDetails>();
    
    public FutureOrderPlan[] getFutureOrderPlanService() {
        String strDebug="FutureOrderPlan: ";
        List<FutureOrderPlan> futureOrderPlanList = new ArrayList<FutureOrderPlan>();
        FutureOrderPlan futureOrderPlanArray[] = null;
        
        getPlanOrderInvDetails();
        futureOrderPlanArray = getFutureOrderPlan();
        //futureOrderPlanList=getFutureOrderPlan();               
        //futureOrderPlanArray=futureOrderPlanList.toArray(new FutureOrderPlan[futureOrderPlanList.size()]);  
        return futureOrderPlanArray;
    }
    public FutureOrderPlan[] getFutureOrderPlan() 
    {
        List p_planOrderInvDetailsList = (List)AdfmfJavaUtilities.getELValue("#{pageFlowScope.planOrderInv}");
        int listSize=p_planOrderInvDetailsList.size();
        List<FutureOrderPlan> futureOrderPlanList = new ArrayList<FutureOrderPlan>();
        int weekWiseSum=0;
        String strDebug=null;
        try{
        //int noOfWeek = 7;//temp change it to 7 once testing completes
        strDebug=strDebug+":listSize:"+listSize;
        String weekNo="1";
        String weekEndingDay="";
        PlanOrderInvDetails planObj = null;
            for(int j=0;j<listSize;j++)
            {
                strDebug=strDebug+":j="+j;
                planObj = (PlanOrderInvDetails)p_planOrderInvDetailsList.get(j);
                if(planObj != null){
                    if( (!weekNo.equalsIgnoreCase(planObj.getWeekNo()+"")))//|| j==listSize-1
                    {
                        strDebug=strDebug+"~Set~";
                        FutureOrderPlan fOP=new FutureOrderPlan();
                        fOP.setWeekNo(weekNo);
                        fOP.setWeekEnd(weekEndingDay);
                        fOP.setQty(Integer.toString(weekWiseSum));  
                        if ("Y".equalsIgnoreCase(planObj.getCausal())) {
                            fOP.setHashValue("*");
                        }
                        futureOrderPlanList.add(fOP);
                        weekWiseSum = Integer.parseInt(planObj.getSalesForecast() == null?"0":planObj.getSalesForecast());
                        //Resetting week wise count flag.
                        weekNo = (String)planObj.getWeekNo();
                    }
                    else  {
                        strDebug=strDebug+"~Add~";
                        weekWiseSum = weekWiseSum + Integer.parseInt(planObj.getSalesForecast() == null?"0":planObj.getSalesForecast());
                        weekEndingDay = planObj.getWeekEnding();
                    }
                }
            }
            strDebug=strDebug+":1";
            FutureOrderPlan fOP=new FutureOrderPlan();
            strDebug=strDebug+":2";
            fOP.setWeekNo(planObj.getWeekNo());
            strDebug=strDebug+":3";
            fOP.setWeekEnd(planObj.getWeekEnding());
            strDebug=strDebug+":4";
            //weekWiseSum = weekWiseSum + Integer.parseInt(planObj.getSalesForecast() == null?"0":planObj.getSalesForecast());
            fOP.setQty(Integer.toString(weekWiseSum)); 
            if ("Y".equalsIgnoreCase(planObj.getCausal())) {
                fOP.setHashValue("*");
            }
            futureOrderPlanList.add(fOP);   
        }
    catch(Exception e){
        strDebug=strDebug+":Err:"+e.getMessage(); 
    }
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.getFutureOrderPlan}", strDebug);
        return futureOrderPlanList.toArray(new FutureOrderPlan[futureOrderPlanList.size()]);  
         //futureOrderPlanList;
    }
    public void resetServiceStatus()
    {
       AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceStatus}", "");
       AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", "");
    }

    public void getPlanOrderInvDetails()
    {
        //int weekNoInt;               
        String strDebug="In getPlanOrderInvDetails:";
        //PlanOrderInvDetails[] planOrderInvDtlArray = null;
        planOrderInvDetailsList = new ArrayList<PlanOrderInvDetails>();
        
        ServiceManager serviceManager = new ServiceManager();
        strDebug=strDebug+":Test:";
        String strServiceHitFlag = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.planOrderSetFlag}");
        strDebug=strDebug+" strServiceHitFlag:"+strServiceHitFlag;
        
        if("N".equalsIgnoreCase(strServiceHitFlag) || strServiceHitFlag==null)
        {
        
        String sellableUPC  = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.itemNumber}");
        String storeId      = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}");
        String buyerId="-999";
        String sourceId="-999";
        strDebug=strDebug+"~sellableUPC:"+sellableUPC+":storeId:"+storeId+":buyerId:"+buyerId+":sourceId:"+sourceId;
        String url = RestURIs.getPlanOrderInvDetails(sellableUPC,storeId,buyerId,sourceId);
        strDebug=strDebug+":url:"+url;

        String strServiceStatus = "";
        String strServiceErrMsg = "";
        resetServiceStatus();
                                
        String jsonArrayAsString = serviceManager.invokeREAD(url);

                
        try {
                JSONObject jsonObject = new JSONObject(jsonArrayAsString);
                if (jsonObject.getString("X_RETURN_STATUS") != null)
                    strServiceStatus = jsonObject.getString("X_RETURN_STATUS");
                
                if (jsonObject.getString("X_RETURN_MSG") != null)
                    strServiceErrMsg = jsonObject.getString("X_RETURN_MSG"); 
        
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", strServiceErrMsg);
            
                JSONObject parent = jsonObject.getJSONObject("P_PO_QTY_TAB");
                JSONArray nodeArray = parent.getJSONArray("P_PO_QTY_TAB_ITEM");
    
                int size = nodeArray.length();
                //int noOfWeek = 7;//temp change it to 7 once testing completes
                strDebug=strDebug+":size:"+size;
                for (int i = 0; i < size; i++) 
                {
                    JSONObject temp = nodeArray.getJSONObject(i);
    
                    String day = null;
                    if (temp.getString("DAY") != null)
                        day = temp.getString("DAY");
    
                    String inventoryDt = null;
                    if (temp.getString("INVENTORY_DATE") != null)
                        inventoryDt = temp.getString("INVENTORY_DATE");
    
                    String salesForecast = null;
                    if (temp.getString("SALES_FORECAST") != null)
                        salesForecast = temp.getString("SALES_FORECAST");
                    
                    String safetyStock = null;
                    if (temp.getString("SAFETY_STOCK") != null)
                        safetyStock = temp.getString("SAFETY_STOCK");
                    
                    String onOrders = null;
                    if (temp.getString("ON_ORDERS") != null)
                        onOrders = temp.getString("ON_ORDERS");
                    
                    String packSize = null;
                    if (temp.getString("PACK_SIZE") != null)
                        packSize = temp.getString("PACK_SIZE");
                    
                    String allocated = null;
                    if (temp.getString("ALLOCATED") != null)
                        allocated = temp.getString("ALLOCATED");
                    
                    String causal = null;
                    if (temp.getString("CAUSAL") != null)
                        causal = temp.getString("CAUSAL");
                    
                    String buyer = null;
                    if (temp.getString("BUYER") != null)
                        buyer = temp.getString("BUYER");
                    
                    String payloadBuyerId = null;
                    if (temp.getString("BUYER_ID") != null)
                        payloadBuyerId = temp.getString("BUYER_ID");                    
                    
                    String source = null;
                    if (temp.getString("SOURCE") != null)
                        source = temp.getString("SOURCE");
                    
                    String payloadSourceId = null;
                    if (temp.getString("SOURCE_ID") != null)
                        payloadSourceId = temp.getString("SOURCE_ID");                       
                    
                    String hashVal=null;
                    if ("Y".equalsIgnoreCase(causal))
                        hashVal="*";
                    
                    String weekNo = null;
                    if (temp.getString("WEEK") != null)
                        weekNo = temp.getString("WEEK"); 
                    
                    String weekEnding = null;
                    if (temp.getString("WEEK_ENDING") != null)
                        weekEnding = temp.getString("WEEK_ENDING");                     
                        
                    ;
                    
                    //weekNoInt=(i/noOfWeek)+1;
                    
                    //weekNo=Integer.toString(weekNoInt);
                    
                    PlanOrderInvDetails planOrdInvDtlRec= 
                        new PlanOrderInvDetails(Integer.toString(i),
                                                weekNo,
                                                day, 
                                                inventoryDt, 
                                                salesForecast, 
                                                safetyStock, 
                                                onOrders, 
                                                packSize, 
                                                allocated, 
                                                causal, 
                                                buyer, 
                                                source,
                                                "N",
                                                payloadBuyerId,
                                                payloadSourceId,
                                                hashVal,
                                                weekEnding);
                    
                    planOrderInvDetailsList.add(planOrdInvDtlRec);                                    
                }
            } catch (Exception e) 
            {
                System.out.println(e.getMessage());
                strDebug = strDebug + ":Error:"+e.getMessage();
            }
            //planOrderInvDtlArray=planOrderInvDetailsList.toArray(new PlanOrderInvDetails[planOrderInvDetailsList.size()]);
    
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.getPlanOrderInvDetails}", strDebug);  
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.planOrderInv}", planOrderInvDetailsList);
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.planOrderSetFlag}", "Y");
    }
    }
    
    public PlanOrderInvDetails[] getDayDetails() 
    {
            List<PlanOrderInvDetails> planOrderInvDetails  = new ArrayList<PlanOrderInvDetails>();
            String strDebug="GetDayDetails: ";
            String strMainDebug="";
            String strBuyerChange = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.buyerChange}");
            strBuyerChange = strBuyerChange +"~N~";
            strMainDebug=strMainDebug+"~DayWiseCalled~"+strBuyerChange;
            PlanOrderInvDetails[] planOrderInvDtlArray = null;
            planOrderInvDetailsList = new ArrayList<PlanOrderInvDetails>();           
                    
            List planOrderInvDetailsList = (List)AdfmfJavaUtilities.getELValue("#{pageFlowScope.planOrderInv}");
            String strPlanOrderSetFlag = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.planOrderSetFlag}");
            String strSelectedIndex = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedWeekIndex}");
                                    
            //strDebug= "strPlanOrderSetFlag: "+strPlanOrderSetFlag;
            try
            {
                //int noOfWeek = 7;//temp change it to 7 once testing completes
                //int startIndex = (Integer.parseInt(strSelectedIndex)-1)*noOfWeek;
                //int endIndex = (startIndex+noOfWeek);
                //strDebug=strDebug+":startIndex:"+startIndex+":endIndex:"+endIndex;
                if("Y".equalsIgnoreCase(strPlanOrderSetFlag))
                {
                    strDebug=strDebug+":Size:"+planOrderInvDetailsList.size();
                    for(int i = 0 ; i < planOrderInvDetailsList.size() ; i++)
                    {
                        
                        PlanOrderInvDetails porid= (PlanOrderInvDetails)planOrderInvDetailsList.get(i);
                        String strWeekNo = AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedWeekIndex}")==null?""
                            :(String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedWeekIndex}");
                        strDebug=strDebug+":i="+i+":strWeekNo:"+strWeekNo+":WekNo:"+porid.getWeekNo();
                        if(strWeekNo.equalsIgnoreCase(porid.getWeekNo())){
                        porid.setExpandCollapseFlag("N");
                        String payloadBuyerId = porid.getBuyerId();
                        String payloadSourceId = porid.getSourceId();
                        String selectedBuyerId=AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedBuyerId}")==null?"-999":
                                               (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedBuyerId}");
                        String selectedSourceId=AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedSourceId}")==null ? "-999":
                                                  (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedSourceId}");
                        boolean canAdd = true;
                        
                        if(!("-999".equalsIgnoreCase(selectedBuyerId))&&!(selectedBuyerId.equalsIgnoreCase(payloadBuyerId)))
                            canAdd = false;
                        
                        if(!("-999".equalsIgnoreCase(selectedSourceId))&&!(selectedSourceId.equalsIgnoreCase(payloadSourceId)))
                            canAdd = false;
                        
                        strDebug= strDebug+ ":i="+i+":payloadBuyerId:"+payloadBuyerId+" payloadSourceId:"+payloadSourceId+
                                  " selectedBuyerId:"+selectedBuyerId+" selectedSourceId:"+selectedSourceId;                      
                        if(canAdd)
                        {
                            strDebug= strDebug+":CanAddTrue";
                            planOrderInvDetails.add(porid);
                        }else{
                            strDebug= strDebug+":CanAddFalse";
                        }
                        }
                    }
                }
            }
            catch (Exception e) 
            {
                        System.out.println(e.getMessage());
                        strDebug = strDebug + ":Error:"+e.getMessage();                        
            }
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", strDebug);            
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.strMainDebug}", strMainDebug);
            
            planOrderInvDtlArray= planOrderInvDetails.toArray(new PlanOrderInvDetails[planOrderInvDetails.size()]);
            return planOrderInvDtlArray;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
