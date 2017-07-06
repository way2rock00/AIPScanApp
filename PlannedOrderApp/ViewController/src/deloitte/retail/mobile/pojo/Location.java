package deloitte.retail.mobile.pojo;

public class Location {
    public Location() {
        super();
    }
    private String locationId;
    private String locationCode;
    private String locationDesc;

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    public String getLocationDesc() {
        return locationDesc;
    }


    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public Location(String locationId,String locationCode, String locationDesc) {
        this.locationId = locationId;
        this.locationCode = locationCode;
        this.locationDesc = locationDesc;
    }

}
