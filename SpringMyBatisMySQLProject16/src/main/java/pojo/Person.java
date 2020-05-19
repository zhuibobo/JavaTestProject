package pojo;

import java.util.Date;

public class Person {
    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private String idcard;

    private Date birthday;

    public Person(Integer id, String name, String sex, Integer age, String idcard, Date birthday) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.idcard = idcard;
        this.birthday = birthday;
    }

    public Person() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        return birthday;
    }

    public void setBrithday(Date birthday) {
        this.birthday = birthday;
    }
}