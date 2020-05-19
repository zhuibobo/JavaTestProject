package pojo;

import java.util.List;

public class Relationship_1toN_pc {
    private Integer id;

    private String name;

    private List<Relationship_1toN_cpu> cpus;

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

    public List<Relationship_1toN_cpu> getCpus() {
        return cpus;
    }

    public void setCpus(List<Relationship_1toN_cpu> cpus) {
        this.cpus = cpus;
    }
}