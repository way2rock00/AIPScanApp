package deloitte.retail.mobile.pojo;

public class FirmOrder { 
    public FirmOrder() {
        super();
    }
    private String odrNumber;
    private String deliveryDate;
    private String quantity;
    private String packSize;
    private String source;
    private String sourceType;
    private String buyer;
    private String price;

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setOdrNumber(String odrNumber) {
        this.odrNumber = odrNumber;
    }

    public String getOdrNumber() {
        return odrNumber;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }
    

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }



    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyer() {
        return buyer;
    }
    
    public FirmOrder(String odrNumber
                    ,String deliveryDate
                    ,String quantity
                    ,String packSize
                    ,String source
                    ,String sourceType
                    ,String buyer
                    ,String price)
    {
        this.odrNumber = odrNumber ;
        this.deliveryDate = deliveryDate ;
        this.quantity = quantity ;
        this.packSize = packSize ;
        this.source = source ;
        this.sourceType = sourceType ;
        this.buyer = buyer ;
        this.price = price ;
    }
}
