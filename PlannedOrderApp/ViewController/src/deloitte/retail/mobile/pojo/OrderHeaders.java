package deloitte.retail.mobile.pojo;

public class OrderHeaders {
    public OrderHeaders() {
        super();
    }
    
    private String recordId;
    private String orderId;
    private String orderNumber;
    private String orderType;
    private String sourceType;
    private String sourceId;
    private String sourceDesc;
    private String destinationType;
    private String destinationId;
    private String destinationDesc;
    private String deliveryDate;
    private String truckId;
    private String orderStatus;
    private String releaseDate;
    private String planner;

    private OrderLine[] orderLines;


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

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceId() {
        return sourceId;
    }


    public void setSourceDesc(String sourceDesc) {
        this.sourceDesc = sourceDesc;
    }

    public String getSourceDesc() {
        return sourceDesc;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public String getDestinationType() {
        return destinationType;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc;
    }

    public String getDestinationDesc() {
        return destinationDesc;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setPlanner(String planner) {
        this.planner = planner;
    }

    public String getPlanner() {
        return planner;
    }

    public void setOrderLines(OrderLine[] orderLines) {
        this.orderLines = orderLines;
    }

    public OrderLine[] getOrderLines() {
        return orderLines;
    }


    public OrderHeaders(String recordId, String orderId, String orderNumber, String orderType, String sourceType,
                        String sourceId, String sourceDesc, String destinationType, String destinationId, String destinationDesc,
                        String deliveryDate, String truckId, String orderStatus, String releaseDate, String planner,
                        OrderLine[] orderLines
                            ) {
        this.recordId = recordId;
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderType = orderType;
        this.sourceType = sourceType;
        this.sourceId = sourceId;
        this.sourceDesc = sourceDesc;
        this.destinationType = destinationType;
        this.destinationId = destinationId;
        this.destinationDesc = destinationDesc;
        this.deliveryDate = deliveryDate;
        this.truckId = truckId;
        this.orderStatus = orderStatus;
        this.releaseDate = releaseDate;
        this.planner = planner;
        this.orderLines = orderLines;
    }
}
