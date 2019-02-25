package springMyBatisSQLServerProject5.pojo;

public class Relationship_1toN_cpu {
    private Integer id;

    private String name;

    private Integer pcId;

    public Relationship_1toN_cpu(Integer id, String name, Integer pcId) {
        this.id = id;
        this.name = name;
        this.pcId = pcId;
    }

    public Relationship_1toN_cpu() {
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

    public Integer getPcId() {
        return pcId;
    }

    public void setPcId(Integer pcId) {
        this.pcId = pcId;
    }
}