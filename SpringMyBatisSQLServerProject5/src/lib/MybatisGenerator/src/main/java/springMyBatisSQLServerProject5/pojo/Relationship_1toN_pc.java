package springMyBatisSQLServerProject5.pojo;

public class Relationship_1toN_pc {
    private Integer id;

    private String name;

    public Relationship_1toN_pc(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Relationship_1toN_pc() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}