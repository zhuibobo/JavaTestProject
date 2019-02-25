package springMyBatisSQLServerProject5.dao;

import springMyBatisSQLServerProject5.pojo.Relationship_1toN_pc;

public interface Relationship_1toN_pcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Relationship_1toN_pc record);

    int insertSelective(Relationship_1toN_pc record);

    Relationship_1toN_pc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relationship_1toN_pc record);

    int updateByPrimaryKey(Relationship_1toN_pc record);
}