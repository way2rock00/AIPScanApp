package deloitte.retail.mobile.pojo;

public class PlannedOrderSummaryInfo {
    public PlannedOrderSummaryInfo() {
        super();
    }
    
    private String recordId;
    private String status;
    private String statusCount;


    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusCount(String statusCount) {
        this.statusCount = statusCount;
    }

    public String getStatusCount() {
        return statusCount;
    }
    
    public PlannedOrderSummaryInfo(String recordId
                                   ,String status
                                   ,String statusCount) {
        this.recordId = recordId;
        this.status = status;
        this.statusCount = statusCount;
        
    }

}
