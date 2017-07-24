package deloitte.retail.mobile.utility;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class RestURIs {
    public RestURIs() {
        super();
    }

    private static String PO_SUMMARY = "/XxRpmGetPoSummary/GetPoSummary_RS";
    //http://app101111.glbsnet.com/XxRpmGetPoSummary/GetPoSummary_RS/-999/305
    private static String LoginURL = "/XxRpmValidateUser/ValidateUser_RS";
    private static String PlannedSummaryURL = "/XxRpmGetPlannedOrdersSummary/GetPlannedOrdersRS";
    private static String OrderHeaderDetailsURL = "/XxRpmGetPlannedOrderDetail/GetOrderDetailRS";
    private static String OrderDetailsURL = "/XxRpmGetPlanOrderDetails/GetPlanOrderDetailsRS";
    private static String OrderSearchURL ="/XxRpmGetAIPOrderHdr/GetAIPOrderHdrRS";
    private static String SourceURL ="/XxRpmGetPlanOrderSourceLov/GetPlanOrderSourceLovRS";
    private static String DestinationURL ="/XxRpmGetPlanOrdDestinationLov/GetPlanOrdDestinationLovRS";
    private static String SKUDetailsURL ="/XxRpmPlannedStoreDaySku/GetPlannedStoreDaySkuRS";    
    private static String ItemDetailsURL="XxRpmGetAIPItemDetails/GetAIPItemDetails_RS";
    private static String ItemQtyUrl ="/XxrpmGetAIPItemQuantities/GetAIPItemQuantities_RS"; // /{P_SELLABLE_UPC}/{P_STORE_NAME}
    private static String PlanOrderInvDtls = "/XxRpmGetAIPPlanOrdInvDetails/GetAIPPlanOrdInvDetails";
    //LOV
    private static String BuyerLOVUrl   = "/XxRpmGetAIPBuyerLov/GetAIPBuyerLov_RS"; // /{P_SELLABLE_UPC}/{P_STORE_NAME}
    private static String SourceLOVUrl  = "/XxRpmGetAIPSourceLov/GetAIPSourceLov_RS"; // /{P_SELLABLE_UPC}/{P_STORE_NAME}
    private static String StoreLOVUrl   = "/XxRpmGetAIPStoreLov/GetAIPStoreLov_RS";
                    
    private static String slash="/";
    
    public static String getItemQtyUrl(String sellableUPC, String storeNm) 
    {
        return ItemQtyUrl+slash+sellableUPC+slash+storeNm;
    }
    public static String getPlanOrderInvDetails(String sellableUPC, String storeId, String buyer, String source) 
    {
        return PlanOrderInvDtls+slash+sellableUPC+slash+storeId+slash+buyer+slash+source;
    }
    
    public static String getLoginURL(String userName,String password,String deviceId, String loginType,String appId)
    {
        return LoginURL+"/"+userName+"/"+password+"/"+deviceId+"/"+loginType+"/"+appId;
    }
    
    public static String getPlannedSummaryURL(String strPlanner)
    {
        return PlannedSummaryURL+"/"+strPlanner;
    }
    
    public static String getOrderHeaderDetailsURL(String strSelectedStatus,String strPlanner)
    {
        return OrderHeaderDetailsURL+"/"+convertString2URLFormat(strSelectedStatus)+"/"+strPlanner;
    }
    
    public static String getOrderDetailsURL(String strSelectedOrderNumber)
    {
        return OrderDetailsURL+"/"+strSelectedOrderNumber;
    }
    
    public static String getSKUDetailsURL(String strCaseUPC,String strSellableUPC,String strDeliveryDate,String strStoreId)
    {
        return SKUDetailsURL+"/"+convertStringToDateFormat(strDeliveryDate+"T")+"/"+strCaseUPC+"/"+strSellableUPC+"/"+strStoreId;
    }
    
    public static String getOrderSearchURL(String strOrderType,String strStatus,String strSource,
                                           String strDestination,String strDelivDateFrom,String strDelivDateTo,
                                           String strOrderFrom,String strOrderTo,String strCaseUPCFrom,String strCaseUPCTo,
                                           String strTruckNum,String strFetchSize,String strPlanner)
    {
        String strSDebug="Search:";
    return OrderSearchURL+"/"+ convertString2URLFormat(strOrderType)+"/"+convertString2URLFormat(strSource)+"/"+strStatus+
        "/"+convertString2URLFormat(strDestination)+"/"+convertStringToDateFormat(strDelivDateFrom+"T")+"/"+convertStringToDateFormat(strDelivDateTo+"T")+
           "/"+strOrderFrom+"/"+strOrderTo+"/"+strCaseUPCFrom+"/"+
        strCaseUPCTo+"/"+strTruckNum+"/"+strFetchSize+"/"+strPlanner;
    }
    
    public static String getLocationURL(String strType)
    {
        String strURL="";
        if("SOURCE".equalsIgnoreCase(strType))
        strURL = SourceURL+"/"+"1";
        else if("DESTINATION".equalsIgnoreCase(strType))
        strURL = DestinationURL+"/"+"1";
        return strURL;
    }   
    
    public static String getScanItemDetailsURL(String strItemNumber)
    {
        return ItemDetailsURL +"/"+strItemNumber;
    }

    public static String convertString2URLFormat(String strInput) 
    {
        String strOutput = "";
        for (int i = 0; i < strInput.length(); i++) {
            char c = strInput.charAt(i);
            if (32 == (int) c) {
                strOutput = strOutput + "%20";
            } else {
                strOutput = strOutput + c;
            }
        }
        return strOutput;
    }
    
    public static String convertStringToDateFormat(String strInput)
    {
        String strRet = "-999";
            try{
        strInput = strInput.substring(0, strInput.indexOf("T"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
            Date d1 = df.parse(strInput);
            df = new SimpleDateFormat("dd-MMM-YYYY");
            strRet = df.format(d1);
            
        }catch(Exception e)
        {
            System.out.println(""+e.getMessage());
        }
        return strRet;
    }
    private static String FirmOrderURL = "/XxRpmGetAIPFirmOrderQuantities/GetAIPFirmOrderQuantities_RS"; 
        public static String getFirmOrderURL(String strSellableUpc, String strStoreName)
        {
            return FirmOrderURL+"/"+strSellableUpc+"/"+strStoreName;
        } 

    public static String getBuyerLOVURI(String storeId,String sellableUPC)
    {
        return BuyerLOVUrl+"/"+storeId+"/"+sellableUPC;
    }
    public static String getSourceLOVURI(String storeId,String sellableUPC)
    {
        return SourceLOVUrl+"/"+storeId+"/"+sellableUPC;
    }
    public static String getStoreLOVURI(String storeId){
        return StoreLOVUrl +"/"+storeId;
    }
}
