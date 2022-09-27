package vn.sapo.statics;

public enum StaticCountry {
    VIETNAM(193, "Việt Nam"),
    THAILAND(175, "Thái Lan");

    public long id;
    public String name;

    private StaticCountry(long id, String name){
        this.id = id;
        this.name = name;
    }
}
