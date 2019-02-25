package com.springmvcproject2.demo.formatter;

import java.util.Date;

public class Customer {
    private Long id;
    private String name;
    private Address address;
    private Date sumbitDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getSumbitDate() {
        return sumbitDate;
    }

    public void setSumbitDate(Date sumbitDate) {
        this.sumbitDate = sumbitDate;
    }
}
