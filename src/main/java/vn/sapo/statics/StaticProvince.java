package vn.sapo.statics;

public enum StaticProvince {
    HANOI(1, "Hà Nội"),
    TPHCM(2, "TP Hồ Chí Minh");

    public long id;
    public String name;

    private StaticProvince(long id, String name){
        this.id = id;
        this.name = name;
    }
}
