package deloitte.retail.mobile.pojo;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class FutureOrderPlan {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public FutureOrderPlan() {
        super();
    }
    public FutureOrderPlan(String weekNo, String weekEnd, String qty) {
        this.weekNo     = weekNo;
        this.weekEnd    = weekEnd;
        this.qty        = qty;
        
    }
    private String seq;
    private String weekNo;
    private String weekEnd;
    private String qty;

    public void setSeq(String seq) {
        String oldSeq = this.seq;
        this.seq = seq;
        propertyChangeSupport.firePropertyChange("seq", oldSeq, seq);
    }

    public String getSeq() {
        return seq;
    }

    public void setWeekNo(String weekNo) {
        String oldWeekNo = this.weekNo;
        this.weekNo = weekNo;
        propertyChangeSupport.firePropertyChange("weekNo", oldWeekNo, weekNo);
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekEnd(String weekEnd) {
        String oldWeekEnd = this.weekEnd;
        this.weekEnd = weekEnd;
        propertyChangeSupport.firePropertyChange("weekEnd", oldWeekEnd, weekEnd);
    }

    public String getWeekEnd() {
        return weekEnd;
    }

    public void setQty(String qty) {
        String oldQty = this.qty;
        this.qty = qty;
        propertyChangeSupport.firePropertyChange("qty", oldQty, qty);
    }

    public String getQty() {
        return qty;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
