package deloitte.retail.mobile.services;

import deloitte.retail.mobile.pojo.OnHandQty;
import deloitte.retail.mobile.pojo.OrderHeaders;
import deloitte.retail.mobile.utility.RestURIs;
import deloitte.retail.mobile.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class OnHandQtyService {
    public OnHandQtyService() 
    {
        super();
    }

    public OnHandQty[] getOnHandQtyServiceMain() 
    {
        String strDebug="S21:";
        String strDebug13="S22:";
                List<OnHandQty> onHandQtyList = new ArrayList<OnHandQty>();
                OnHandQty onHandQtyArray[] = null;
                
                String strServiceStatus = "";
                String strServiceErrMsg = "";
                ServiceManager serviceManager = new ServiceManager();
                String sellableUPC  = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.itemNumber}");
                String storeId      = (String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedStoreId}");
                strDebug=strDebug+"\n Item: "+sellableUPC;
                strDebug=strDebug+"\n store: "+storeId;
                String url = RestURIs.getItemQtyUrl(sellableUPC ,storeId);
                strDebug = strDebug+" --URL: -- "+url+" storeId:"+storeId;
                String jsonArrayAsString = serviceManager.invokeREAD(url);
                strDebug = strDebug +"~"+jsonArrayAsString;
                try 
                {
                            strDebug = strDebug +"\n:2:";
//                            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
                    strDebug = strDebug +"\n:3:";
                            JSONObject jsonObjOutPara = new JSONObject(jsonArrayAsString); //jsonObject.getJSONObject("OutputParameters");
                    strDebug = strDebug +"\n:4:";
                            strDebug= strDebug+"\n";
                            strDebug= strDebug+"--On Hand Qty"+ jsonObjOutPara.getString("P_ON_HAND_QUANTITY")+"--";
                    //Take handle of the parent Json code and then if it has child 
                    //take the getJSONObject
                    strDebug= strDebug+"--\nX_RETURN_STATUS"+ jsonObjOutPara.getString("X_RETURN_STATUS")+"--";
                            
                            if (jsonObjOutPara.getString("X_RETURN_STATUS") != null)
                            {
                                strServiceStatus = jsonObjOutPara.getString("X_RETURN_STATUS");
                                strDebug = strDebug +"\n:5:";
                            }    
                            
                    strDebug = strDebug +"\n:5:";
                            if (jsonObjOutPara.getString("X_RETURN_MSG") != null)
                                strServiceErrMsg = jsonObjOutPara.getString("X_RETURN_MSG");
                    
                            strDebug = strDebug + ":strServiceStatus:"+strServiceStatus+":strServiceErrMsg:"+strServiceErrMsg;
                            AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", strServiceErrMsg);
                                
                            JSONObject temp =jsonObjOutPara;

                                    String onHandQty = null;
                                    if (temp.getString("P_ON_HAND_QUANTITY") != null)
                                        onHandQty = temp.getString("P_ON_HAND_QUANTITY");
                                    else if (onHandQty == null)
                                        onHandQty = "0";                    
                                    strDebug13=strDebug13+"--OnHand-- "+onHandQty;
                                        
                                    String totFirmOrderQty = null;
                                    if (temp.getString("P_TOTAL_FIRM_ORDER_QUANTITY") != null)
                                        totFirmOrderQty = temp.getString("P_TOTAL_FIRM_ORDER_QUANTITY");
                                    else if (totFirmOrderQty == null)
                                        totFirmOrderQty = "0";
                    
                                    String totPlanOrdQty = null;
                                    if (temp.getString("P_TOTAL_PLANNED_ORDER_QUANTITY") != null)
                                        totPlanOrdQty = temp.getString("P_TOTAL_PLANNED_ORDER_QUANTITY");
                                    else if (totPlanOrdQty == null)
                                        totPlanOrdQty = "0";

                                    String dispMin = null;
                                    if (temp.getString("P_DISPLAY_MIN") != null)
                                        dispMin = temp.getString("P_DISPLAY_MIN");
                                    else if (dispMin == null)
                                        dispMin = "0"; 
                                    
                                    String presentationMin = null;
                                    if (temp.getString("P_PRESENTATION_MIN") != null)
                                        presentationMin = temp.getString("P_PRESENTATION_MIN");
                                    else if (presentationMin == null)
                                        presentationMin = "0"; 
                                    
                                    String price = null;
                                    if (temp.getString("P_PRICE") != null)
                                        price = temp.getString("P_PRICE");
                                    
                                    String causal = null;
                                    if (temp.getString("P_CAUSAL") != null)
                                        causal = temp.getString("P_CAUSAL");
                
                                OnHandQty onHandQtyObj=new OnHandQty(onHandQty, totFirmOrderQty, totPlanOrdQty, dispMin, presentationMin, price, causal);
                                onHandQtyList.add(onHandQtyObj);
                }
                
                catch (Exception e) 
                {
                                System.out.println(e.getMessage());
                                strDebug = strDebug +":Error:"+e.getMessage();
                }    
                strDebug = strDebug +":10:";
                onHandQtyArray = onHandQtyList.toArray(new OnHandQty[onHandQtyList.size()]);
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.strDebug}", strDebug);
                return onHandQtyArray;
    }
    
    private void resetServiceStatus() 
    {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceStatus}", "");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceErrMsg}", "");
    }

    }