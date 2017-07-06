package deloitte.retail.mobile.pojo;

public class OrderLine {
    public OrderLine() {
        super();
    }

    private String recordId;
    private String orderId;
    private String lineId;
    private String caseUPC;
    private String caseUPCDesc;
    private String sellableUPC;
    private String sellableUPCDesc;
    private String itemClass;
    private String subClass;
    private String quantity;
    private String uom;

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineId() {
        return lineId;
    }

    public void setCaseUPC(String caseUPC) {
        this.caseUPC = caseUPC;
    }

    public String getCaseUPC() {
        return caseUPC;
    }

    public void setCaseUPCDesc(String caseUPCDesc) {
        this.caseUPCDesc = caseUPCDesc;
    }

    public String getCaseUPCDesc() {
        return caseUPCDesc;
    }

    public void setSellableUPC(String sellableUPC) {
        this.sellableUPC = sellableUPC;
    }

    public String getSellableUPC() {
        return sellableUPC;
    }

    public void setSellableUPCDesc(String sellableUPCDesc) {
        this.sellableUPCDesc = sellableUPCDesc;
    }

    public String getSellableUPCDesc() {
        return sellableUPCDesc;
    }


    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public String getSubClass() {
        return subClass;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getUom() {
        return uom;
    }

    public OrderLine(String recordId, String orderId, String lineId, String caseUPC, String caseUPCDesc,
                     String sellableUPC, String sellableUPCDesc, String itemClass, String subClass, String quantity,
                     String uom) {
        this.recordId = recordId;
        this.orderId = orderId;
        this.lineId = lineId;
        this.caseUPC = caseUPC;
        this.caseUPCDesc = caseUPCDesc;
        this.sellableUPC = sellableUPC;
        this.sellableUPCDesc = sellableUPCDesc;
        this.itemClass = itemClass;
        this.subClass = subClass;
        this.quantity = quantity;
        this.uom = uom;
    }


}
