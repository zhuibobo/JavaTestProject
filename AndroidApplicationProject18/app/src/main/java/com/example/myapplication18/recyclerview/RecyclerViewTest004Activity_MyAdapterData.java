package com.example.myapplication18.recyclerview;

public class RecyclerViewTest004Activity_MyAdapterData {
    String name;
    String sex;
    int age;
    String desc;

    public RecyclerViewTest004Activity_MyAdapterData(String name, String sex, int age, String desc) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.desc = desc;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
