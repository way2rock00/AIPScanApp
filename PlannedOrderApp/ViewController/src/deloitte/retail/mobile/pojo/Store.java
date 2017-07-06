package deloitte.retail.mobile.pojo;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class Store {
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public Store() {
        super();
    }
    private String storeId;
    private String store;
    
    public Store(String storeId, String store) {
        this.storeId = storeId;
        this.store = store;
    }

    public void setStoreId(String storeId) {
        String oldStoreId = this.storeId;
        this.storeId = storeId;
        propertyChangeSupport.firePropertyChange("storeId", oldStoreId, storeId);
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStore(String store) {
        String oldStore = this.store;
        this.store = store;
        propertyChangeSupport.firePropertyChange("store", oldStore, store);
    }

    public String getStore() {
        return store;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
