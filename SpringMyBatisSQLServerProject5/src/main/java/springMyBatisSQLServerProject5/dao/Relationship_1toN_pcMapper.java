package springMyBatisSQLServerProject5.dao;

import springMyBatisSQLServerProject5.pojo.Relationship_1to1_pc;
import springMyBatisSQLServerProject5.pojo.Relationship_1toN_pc;

public interface Relationship_1toN_pcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Relationship_1toN_pc record);

    int insertSelective(Relationship_1toN_pc record);

    Relationship_1toN_pc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relationship_1toN_pc record);

    int updateByPrimaryKey(Relationship_1toN_pc record);

    int insert2table(Relationship_1toN_pc record);

    void delete2table(Integer id);

    Relationship_1toN_pc select2table001(Integer id);

    Relationship_1toN_pc select2table002(Integer id);
}