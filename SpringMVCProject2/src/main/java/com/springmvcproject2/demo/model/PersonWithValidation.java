package com.springmvcproject2.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class PersonWithValidation implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String sex;

    private int age;

    private String tel;
    private Date birthday;

    /*public PersonWithValidation() {
    }*/

    /*public PersonWithValidation(@NotNull String name, @NotNull String sex, @NotNull int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public PersonWithValidation(@NotNull String name, @NotNull String sex, @NotNull int age, @NotNull @Size(min = 11, max = 12) String tel, Date birthday) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.birthday = birthday;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
