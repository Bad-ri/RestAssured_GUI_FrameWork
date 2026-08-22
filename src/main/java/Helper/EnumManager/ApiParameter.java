package Helper.EnumManager;

public enum ApiParameter {
    PARAMETER;
    private String description;
    private String id;

    public void setReq(String description){
        this.description = description;
    }

    public void setRes(String id){
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public String getId() {
        return id;
    }
}
