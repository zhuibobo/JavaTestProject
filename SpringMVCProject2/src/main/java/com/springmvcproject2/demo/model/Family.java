package com.springmvcproject2.demo.model;

import java.util.List;

public class Family {
    public String address;
    public List<Person> personList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
