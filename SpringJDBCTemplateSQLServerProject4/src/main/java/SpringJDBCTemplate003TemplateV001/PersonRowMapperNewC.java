package SpringJDBCTemplate003TemplateV001;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapperNewC implements RowMapper<PersonNewC> {
    @Override
    public PersonNewC mapRow(ResultSet resultSet, int i) throws SQLException {
        PersonNewC person=new PersonNewC();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setBrithday(resultSet.getString("brithday"));
        person.setIdcard(resultSet.getString("idcard"));
        person.setSex(resultSet.getString("sex"));
        person.setNewc(resultSet.getString("newc"));
        return person;
    }
}
