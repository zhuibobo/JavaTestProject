package SpringJDBCTemplate003TemplateV001;

import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person=new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setBrithday(resultSet.getString("brithday"));
        person.setIdcard(resultSet.getString("idcard"));
        person.setSex(resultSet.getString("sex"));
        resultSet.getMetaData();
        return person;
    }
}
