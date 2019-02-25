package SpringJDBCTemplate001Conn;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnAndInsertTest {
    @Test
    public void InsertPersonNormal100() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p100=connAndSearch.InsertPersonNormal(100,true);
        System.out.println(p100);
    }

    @Test
    public void InsertPersonNormal1000() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p1010=connAndSearch.InsertPersonNormal(1000,true);
        System.out.println(p1010);
    }

    @Test
    public void InsertPersonNormal10000() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p1010=connAndSearch.InsertPersonNormal(10000,true);
        System.out.println(p1010);
    }

    @Test
    public void InsertPersonNormal10000NotAutoCommit() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p1010=connAndSearch.InsertPersonNormal(10000,false);
        System.out.println(p1010);
    }

    @Test
    public void insertPersonBatch100() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p1010=connAndSearch.InsertPersonBatch(100);
        System.out.println(p1010);
    }

    @Test
    public void insertPersonBatch1000() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p1010=connAndSearch.InsertPersonBatch(1000);
        System.out.println(p1010);
    }

    @Test
    public void insertPersonBatch10000() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p1010=connAndSearch.InsertPersonBatch(10000);
        System.out.println(p1010);
    }

    @Test
    public void insertPersonPrepared1000() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p1010=connAndSearch.InsertPersonPrepared(1000,false);
        System.out.println(p1010);
    }

    @Test
    public void insertPersonPrepared10000AutoCommit() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p1010=connAndSearch.InsertPersonPrepared(10000,true);
        System.out.println(p1010);
    }

    @Test
    public void insertPersonPrepared10000NotAutoCommit() {
        ConnAndInsert connAndSearch=new ConnAndInsert();
        long p1010=connAndSearch.InsertPersonPrepared(10000,false);
        System.out.println(p1010);
    }
}