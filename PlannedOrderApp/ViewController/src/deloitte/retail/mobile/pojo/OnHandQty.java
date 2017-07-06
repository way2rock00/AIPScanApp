package deloitte.retail.mobile.pojo;

public class OnHandQty 
{
    public OnHandQty() 
    {
        super();
    }
    public OnHandQty(String onHandQty,String totFirmOrderQty,String totPlnOrdQty, String dispMin,String presttnMin,String price, String causal) 
    {
        this.onHandQty=  onHandQty;
        this.totFirmOrderQty=totFirmOrderQty;
        this.totPlnOrdQty=totPlnOrdQty;
        this.dispMin=dispMin;
        this.presttnMin=presttnMin;
        this.price=price;
        this.causal=causal;            
    }     

    private String onHandQty;       //ON_HAND_QUANTITY
    private String totFirmOrderQty; //TOTAL_FIRM_ORDER_QUANTITY
    private String totPlnOrdQty;    //TOTAL_PLANNED_ORDER_QUANTITY;

    public void setOnHandQty(String onHandQty) {
        this.onHandQty = onHandQty;
    }

    public String getOnHandQty() {
        return onHandQty;
    }

    public void setTotFirmOrderQty(String totFirmOrderQty) {
        this.totFirmOrderQty = totFirmOrderQty;
    }

    public String getTotFirmOrderQty() {
        return totFirmOrderQty;
    }

    public void setTotPlnOrdQty(String totPlnOrdQty) {
        this.totPlnOrdQty = totPlnOrdQty;
    }

    public String getTotPlnOrdQty() {
        return totPlnOrdQty;
    }

    public void setDispMin(String dispMin) {
        this.dispMin = dispMin;
    }

    public String getDispMin() {
        return dispMin;
    }

    public void setPresttnMin(String presttnMin) {
        this.presttnMin = presttnMin;
    }

    public String getPresttnMin() {
        return presttnMin;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setCausal(String causal) {
        this.causal = causal;
    }

    public String getCausal() {
        return causal;
    }
    private String dispMin;     //DISPLAY_MIN
    private String presttnMin;//PRESENTATION_MIN
    private String price;           //PRICE
    private String causal;          //CAUSAL

}
