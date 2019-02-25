package springMyBatisSQLServerProject5.dao;

import springMyBatisSQLServerProject5.pojo.Relationship_1to1_pc;

public interface Relationship_1to1_pcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Relationship_1to1_pc record);

    int insertSelective(Relationship_1to1_pc record);

    Relationship_1to1_pc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relationship_1to1_pc record);

    int updateByPrimaryKey(Relationship_1to1_pc record);
}