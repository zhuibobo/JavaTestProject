package dao;


import pojo.Relationship_1toN_cpu;

public interface Relationship_1toN_cpuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Relationship_1toN_cpu record);

    int insertSelective(Relationship_1toN_cpu record);

    Relationship_1toN_cpu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relationship_1toN_cpu record);

    int updateByPrimaryKey(Relationship_1toN_cpu record);
}