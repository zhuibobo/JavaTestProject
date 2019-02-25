package springMyBatisSQLServerProject5.dao;

import springMyBatisSQLServerProject5.pojo.PersonFieldToProperty;

public interface PersonFieldToPropertyMapper {
    int deleteByPrimaryKey(Integer person_id);

    int insert(PersonFieldToProperty record);

    int insertSelective(PersonFieldToProperty record);

    PersonFieldToProperty selectByPrimaryKey(Integer person_id);

    int updateByPrimaryKeySelective(PersonFieldToProperty record);

    int updateByPrimaryKey(PersonFieldToProperty record);
}