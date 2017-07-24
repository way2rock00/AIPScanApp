package deloitte.retail.mobile.pojo;

import java.util.Comparator;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class PlanOrderInvDetails {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PlanOrderInvDetails() {
        super();
    }

    public PlanOrderInvDetails(String seq, String weekNo, String day, String inventoryDt, String salesForecast, String safetyStock,
                               String onOrders, String packSize, String allocated, String causal, String buyer,
                               String source, String expandCollapseFlag,String buyerId,String sourceId, String hashVal,
                               String weekEnding) 
    {
        super();
        this.seq=seq;
        this.weekNo=weekNo;
        this.day = day;
        this.inventoryDt = inventoryDt;
        this.salesForecast = salesForecast;
        this.safetyStock = safetyStock;
        this.onOrders = onOrders;
        this.packSize = packSize;
        this.allocated = allocated;
        this.causal = causal;
        this.buyer = buyer;
        this.source = source;
        this.buyerId = buyerId;
        this.sourceId = sourceId;
        this.expandCollapseFlag = expandCollapseFlag;
        this.hashVal=hashVal;
        this.weekEnding=weekEnding;
    }

    public void setHashVal(String hashVal) {
        String oldHashVal = this.hashVal;
        this.hashVal = hashVal;
        propertyChangeSupport.firePropertyChange("hashVal", oldHashVal, hashVal);
    }

    public String getHashVal() {
        return hashVal;
    }

    public void setPlanOrderDayComparator(Comparator<PlanOrderInvDetails> planOrderDayComparator) {
        Comparator<PlanOrderInvDetails> oldPlanOrderDayComparator = PlanOrderInvDetails.planOrderDayComparator;
        PlanOrderInvDetails.planOrderDayComparator = planOrderDayComparator;
        propertyChangeSupport.firePropertyChange("planOrderDayComparator", oldPlanOrderDayComparator,
                                                 planOrderDayComparator);
    }

    public static Comparator<PlanOrderInvDetails> getPlanOrderDayComparator() {
        return planOrderDayComparator;
    }

    private String seq;
    private String weekNo;
    private String day;
    private String inventoryDt;
    private String salesForecast;
    private String hashVal;
    
    public void setExpandCollapseFlag(String expandCollapseFlag) {
        String oldExpandCollapseFlag = this.expandCollapseFlag;
        this.expandCollapseFlag = expandCollapseFlag;
        propertyChangeSupport.firePropertyChange("expandCollapseFlag", oldExpandCollapseFlag, expandCollapseFlag);
    }

    public String getExpandCollapseFlag() {
        return expandCollapseFlag;
    }
    private String safetyStock;
    private String onOrders;
    private String packSize;
    private String allocated;
    private String causal;
    private String buyer;
    private String buyerId;
    private String source;
    private String sourceId;
    private String expandCollapseFlag;
    private String weekEnding;

    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        PropertyChangeSupport oldPropertyChangeSupport = this.propertyChangeSupport;
        this.propertyChangeSupport = propertyChangeSupport;
        propertyChangeSupport.firePropertyChange("propertyChangeSupport", oldPropertyChangeSupport,
                                                 propertyChangeSupport);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void setSeq(String seq) {
        String oldSeq = this.seq;
        this.seq = seq;
        propertyChangeSupport.firePropertyChange("seq", oldSeq, seq);
    }

    public String getSeq() {
        return seq;
    }

    public void setDay(String day) {
        String oldDay = this.day;
        this.day = day;
        propertyChangeSupport.firePropertyChange("day", oldDay, day);
    }

    public String getDay() {
        return day;
    }

    public void setInventoryDt(String inventoryDt) {
        String oldInventoryDt = this.inventoryDt;
        this.inventoryDt = inventoryDt;
        propertyChangeSupport.firePropertyChange("inventoryDt", oldInventoryDt, inventoryDt);
    }

    public String getInventoryDt() {
        return inventoryDt;
    }

    public void setSalesForecast(String salesForecast) {
        String oldSalesForecast = this.salesForecast;
        this.salesForecast = salesForecast;
        propertyChangeSupport.firePropertyChange("salesForecast", oldSalesForecast, salesForecast);
    }

    public String getSalesForecast() {
        return salesForecast;
    }

    public void setSafetyStock(String safetyStock) {
        String oldSafetyStock = this.safetyStock;
        this.safetyStock = safetyStock;
        propertyChangeSupport.firePropertyChange("safetyStock", oldSafetyStock, safetyStock);
    }

    public String getSafetyStock() {
        return safetyStock;
    }

    public void setOnOrders(String onOrders) {
        String oldOnOrders = this.onOrders;
        this.onOrders = onOrders;
        propertyChangeSupport.firePropertyChange("onOrders", oldOnOrders, onOrders);
    }

    public String getOnOrders() {
        return onOrders;
    }

    public void setPackSize(String packSize) {
        String oldPackSize = this.packSize;
        this.packSize = packSize;
        propertyChangeSupport.firePropertyChange("packSize", oldPackSize, packSize);
    }

    public String getPackSize() {
        return packSize;
    }

    public void setAllocated(String allocated) {
        String oldAllocated = this.allocated;
        this.allocated = allocated;
        propertyChangeSupport.firePropertyChange("allocated", oldAllocated, allocated);
    }

    public String getAllocated() {
        return allocated;
    }

    public void setCausal(String causal) {
        String oldCausal = this.causal;
        this.causal = causal;
        propertyChangeSupport.firePropertyChange("causal", oldCausal, causal);
    }

    public String getCausal() {
        return causal;
    }

    public void setBuyer(String buyer) {
        String oldBuyer = this.buyer;
        this.buyer = buyer;
        propertyChangeSupport.firePropertyChange("buyer", oldBuyer, buyer);
    }

    public String getBuyer() {
        return buyer;
    }

    public void setSource(String source) {
        String oldSource = this.source;
        this.source = source;
        propertyChangeSupport.firePropertyChange("source", oldSource, source);
    }

    public String getSource() {
        return source;
    }
    
    public void setWeekNo(String weekNo) {
        String oldWeekNo = this.weekNo;
        this.weekNo = weekNo;
        propertyChangeSupport.firePropertyChange("weekNo", oldWeekNo, weekNo);
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void setBuyerId(String buyerId) {
        String oldBuyerId = this.buyerId;
        this.buyerId = buyerId;
        propertyChangeSupport.firePropertyChange("buyerId", oldBuyerId, buyerId);
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setSourceId(String sourceId) {
        String oldSourceId = this.sourceId;
        this.sourceId = sourceId;
        propertyChangeSupport.firePropertyChange("sourceId", oldSourceId, sourceId);
    }

    public String getSourceId() {
        return sourceId;
    }


   public void setWeekEnding(String weekEnding) {
        String oldWeekEnding = this.weekEnding;
        this.weekEnding = weekEnding;
        propertyChangeSupport.firePropertyChange("weekEnding", oldWeekEnding, weekEnding);
    }

    public String getWeekEnding() {
        return weekEnding;
    }

    public static Comparator<PlanOrderInvDetails> planOrderDayComparator=new Comparator<PlanOrderInvDetails>() {
            public int compare(PlanOrderInvDetails p1, PlanOrderInvDetails p2) {

                       int day1 = Integer.parseInt(p1.getDay());
                       int day2 = Integer.parseInt(p2.getDay());

                       /*For ascending order*/
                       return day1-day2;
        }};
    
}
