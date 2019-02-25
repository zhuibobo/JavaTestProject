package SpringJDBCTemplate002DataSource;

import java.util.Date;

/**
 * @Author: zhuangrb
 * @Date: 2017/12/23
 * @Description:
 * @Version 1.0.0
 */
public class PersonAnnotation {
    private int id;
    private String name;
    private String sex;
    private int age;
    private String idcard;
    private Date brithday;


    public int getId() {
        return id;
    }

    @MYField(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @MYField(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    @MYField(name = "sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    @MYField(name = "age")
    public void setAge(int age) {
        this.age = age;
    }

    public String getIdcard() {
        return idcard;
    }

    @MYField(name = "idcard")
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getBrithday() {
        return brithday;
    }

    @MYField(name = "brithday")
    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    @Override
    public String toString() {
        return "name:"+this.getName()+" id:"+this.getId();
    }
}
