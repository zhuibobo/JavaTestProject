package springMyBatisSQLServerProject5.dao;

import springMyBatisSQLServerProject5.pojo.Relationship_1to1_cpu;

public interface Relationship_1to1_cpuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Relationship_1to1_cpu record);

    int insertSelective(Relationship_1to1_cpu record);

    Relationship_1to1_cpu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relationship_1to1_cpu record);

    int updateByPrimaryKey(Relationship_1to1_cpu record);
}