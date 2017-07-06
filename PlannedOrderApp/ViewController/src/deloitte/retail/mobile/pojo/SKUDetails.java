package deloitte.retail.mobile.pojo;

public class SKUDetails {
    public SKUDetails() {
        super();
    }
    private String recordId;
    private String caseUPC;
    private String deliveryDate;
    private String onHandQyt;
    private String onOrderQty;
    private String allocatedQty;
    private String salesForecastQty;
    private String safetyStockQty;
    private String packSize;


    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setCaseUPC(String caseUPC) {
        this.caseUPC = caseUPC;
    }

    public String getCaseUPC() {
        return caseUPC;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setOnHandQyt(String onHandQyt) {
        this.onHandQyt = onHandQyt;
    }

    public String getOnHandQyt() {
        return onHandQyt;
    }

    public void setOnOrderQty(String onOrderQty) {
        this.onOrderQty = onOrderQty;
    }

    public String getOnOrderQty() {
        return onOrderQty;
    }

    public void setAllocatedQty(String allocatedQty) {
        this.allocatedQty = allocatedQty;
    }

    public String getAllocatedQty() {
        return allocatedQty;
    }

    public void setSalesForecastQty(String salesForecastQty) {
        this.salesForecastQty = salesForecastQty;
    }

    public String getSalesForecastQty() {
        return salesForecastQty;
    }

    public void setSafetyStockQty(String safetyStockQty) {
        this.safetyStockQty = safetyStockQty;
    }

    public String getSafetyStockQty() {
        return safetyStockQty;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public String getPackSize() {
        return packSize;
    }

    public SKUDetails(String recordId,String caseUPC, String deliveryDate, String onHandQyt, String onOrderQty, String allocatedQty,
                      String salesForecastQty, String safetyStockQty, String packSize) {
        this.recordId = recordId;
        this.caseUPC = caseUPC;
        this.deliveryDate = deliveryDate;
        this.onHandQyt = onHandQyt;
        this.onOrderQty = onOrderQty;
        this.allocatedQty = allocatedQty;
        this.salesForecastQty = salesForecastQty;
        this.safetyStockQty = safetyStockQty;
        this.packSize = packSize;
    }


}
