package springMyBatisSQLServerProject5.dao;

import springMyBatisSQLServerProject5.pojo.Person;

public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    void deleteAll();

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}