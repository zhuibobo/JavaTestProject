package dao;


import pojo.PersonFieldToProperty;

public interface PersonFieldToPropertyMapper {
    int deleteByPrimaryKey(Integer person_id);

    int insert(PersonFieldToProperty record);

    int insertSelective(PersonFieldToProperty record);

    PersonFieldToProperty selectByPrimaryKey(Integer person_id);

    int updateByPrimaryKeySelective(PersonFieldToProperty record);

    int updateByPrimaryKey(PersonFieldToProperty record);
}