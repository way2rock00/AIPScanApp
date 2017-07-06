package deloitte.retail.mobile.pojo;

public class Source {
    public Source() {
        super();
    }
    private String sourceId;
    private String source;


    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }


    public Source(String sourceId, String source) {
        this.sourceId = sourceId;
        this.source = source;
    }
}
