package pojo;

public class Relationship_1to1_cpu {
    private Integer id;

    private String name;

    public Relationship_1to1_cpu(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Relationship_1to1_cpu() {
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