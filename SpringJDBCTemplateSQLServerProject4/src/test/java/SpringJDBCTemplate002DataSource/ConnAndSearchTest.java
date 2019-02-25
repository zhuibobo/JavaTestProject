package SpringJDBCTemplate002DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import java.util.List;
import java.util.Map;

public class ConnAndSearchTest {

    @Test
    public void queryName() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        String name=connAndSearch.QueryName(dataSource);
        System.out.println(name);
        Assert.assertEquals("zrb",name);
    }

    @Test
    public void queryList100() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        List<Map> mapList=connAndSearch.QueryListMap(100,dataSource);
        Assert.assertEquals(100,mapList.size());
        //System.out.println(mapList);
    }

    @Test
    public void queryList1000() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        List<Map> mapList=connAndSearch.QueryListMap(1000,dataSource);
        Assert.assertEquals(1000,mapList.size());
        //System.out.println(mapList);
    }

    @Test
    public void queryList10000() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        List<Map> mapList=connAndSearch.QueryListMap(10000,dataSource);
        Assert.assertEquals(10000,mapList.size());
        System.out.println(mapList.get(9999));
    }

    @Test
    public void queryListPerson() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        List<Person> mapList=connAndSearch.QueryListPerson(10000,dataSource);
        Assert.assertEquals(10000,mapList.size());
        System.out.println(mapList.get(9999).toString());
    }

    @Test
    public void queryListPersonBrithday() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        List<PersonBrithday> mapList=connAndSearch.QueryListPersonBrithday(10000,dataSource);
        Assert.assertEquals(10000,mapList.size());
        System.out.println(mapList.get(9999).toString());
    }

    @Test
    public void queryListPersonBrithdayConvertByClassInfo() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(DIConfig002.class);
        DataSource dataSource=annotationConfigApplicationContext.getBean(DataSource.class);
        ConnAndSearch connAndSearch=new ConnAndSearch();
        List<PersonBrithday> mapList=connAndSearch.QueryListPersonBrithdayConvertByClassInfo(10,dataSource);
        Assert.assertEquals(10,mapList.size());
        System.out.println(mapList.get(9).toString());
    }
}