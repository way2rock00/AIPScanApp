package deloitte.retail.mobile.pojo;

public class OrderHeader {
    public OrderHeader() {
        super();
    }
    private String recordId;
    private String orderId;
    private String orderNumber;
    private String orderType;
    private String sourceType;
    private String sourceId;
    private String destinationType;
    private String destinationId;
    private String destinationDesc;
    private String deliveryDate;
    private String truckId;
    private String orderStatus;
    private String releaseDate;
    private String planner;
    private OrderLine[] orderLines;


    public OrderHeader(String recordId, String orderId, String orderNumber, String orderType, String sourceType,
                        String sourceId, String destinationType, String destinationId, String destinationDesc,
                        String deliveryDate, String truckId, String orderStatus, String releaseDate, String planner,OrderLine[] orderLines) {

        this.recordId = recordId;
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderType = orderType;
        this.sourceType = sourceType;
        this.sourceId = sourceId;
        this.destinationType = destinationType;
        this.destinationId = destinationId;
        this.destinationDesc = destinationDesc;
        this.deliveryDate = deliveryDate;
        this.truckId = truckId;
        this.orderStatus = orderStatus;
        this.releaseDate = releaseDate;
        this.planner = planner;
        this.orderLines=orderLines;
    }


}
