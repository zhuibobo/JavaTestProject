package SpringCloudProject12_Eureka_Consumer1.dao;


import SpringCloudProject12_Eureka_Consumer1.pojo.Relationship_1to1_pc;

public interface Relationship_1to1_pcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Relationship_1to1_pc record);

    int insertSelective(Relationship_1to1_pc record);

    Relationship_1to1_pc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relationship_1to1_pc record);

    int updateByPrimaryKey(Relationship_1to1_pc record);

    int insert2table(Relationship_1to1_pc record);

    void update2table(Relationship_1to1_pc record);

    Relationship_1to1_pc select2table001(Integer id);

    Relationship_1to1_pc select2table002(Integer id);
}