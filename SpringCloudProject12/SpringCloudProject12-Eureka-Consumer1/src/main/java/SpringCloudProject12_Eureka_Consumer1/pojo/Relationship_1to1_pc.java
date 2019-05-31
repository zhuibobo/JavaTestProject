package SpringCloudProject12_Eureka_Consumer1.pojo;

public class Relationship_1to1_pc {
    private Integer id;

    private String name;

    private String cpuId;

    private Relationship_1to1_cpu cpu;

    public Relationship_1to1_pc(Integer id, String name, String cpuId) {
        this.id = id;
        this.name = name;
        this.cpuId = cpuId;
    }

    public Relationship_1to1_pc() {
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

    public String getCpuId() {
        return cpuId;
    }

    public void setCpuId(String cpuId) {
        this.cpuId = cpuId == null ? null : cpuId.trim();
    }

    public Relationship_1to1_cpu getCpu() {
        return cpu;
    }

    public void setCpu(Relationship_1to1_cpu cpu) {
        this.cpu = cpu;
    }
}