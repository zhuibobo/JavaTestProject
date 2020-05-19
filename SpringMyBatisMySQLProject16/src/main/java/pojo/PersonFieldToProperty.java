package pojo;

import java.util.Date;

public class PersonFieldToProperty {
    private Integer person_id;

    private String person_name;

    private String sex;

    private Integer age;

    private String idcard;

    private Date brithday;

    public PersonFieldToProperty(Integer person_id, String person_name, String sex, Integer age, String idcard, Date brithday) {
        this.person_id = person_id;
        this.person_name = person_name;
        this.sex = sex;
        this.age = age;
        this.idcard = idcard;
        this.brithday = brithday;
    }

    public PersonFieldToProperty() {
        super();
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name == null ? null : person_name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }
}