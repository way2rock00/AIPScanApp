package deloitte.retail.mobile.pojo;

public class SpringboardItems {
    public SpringboardItems() {
        super();
    }
    String featureName;
    String featureId;
    String iconName;


    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getFeatureId() {
        return featureId;
    }


    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconName() {
        return iconName;
    }


    public SpringboardItems( String featureId,String featureName, String iconName) {
       this.featureName = featureName;
        this.featureId = featureId;
        this.iconName = iconName;
    }

}
