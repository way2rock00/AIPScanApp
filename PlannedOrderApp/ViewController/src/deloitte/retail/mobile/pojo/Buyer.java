package deloitte.retail.mobile.pojo;

public class Buyer {
    public Buyer() {
        super();
    }
    private String buyer;
    private String buyerId;


    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyer() {
        return buyer;
    }


    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public Buyer(String buyerId,String buyer) {
        this.buyerId = buyerId;
        this.buyer = buyer;
    }

}