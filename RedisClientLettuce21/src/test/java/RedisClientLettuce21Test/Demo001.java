package RedisClientLettuce21Test;
import io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisHashCommands;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Demo001 {

    RedisClient redisClient;
    StatefulRedisConnection<String, String> connection;

    public StatefulRedisConnection<String, String> initConnection(){
        redisClient = RedisClient.create("redis://@127.0.0.1:6379/0");
        connection = redisClient.connect();
        return connection;
    }

    public void closeConnection(){
        connection.close();
        redisClient.shutdown();
    }


    @Test
    public void testConn(){
        //RedisClient redisClient = RedisClient.create("redis://password@127.0.0.1:6379/0");
        initConnection();

        RedisCommands<String, String> syncCommands = connection.sync();
        String code =syncCommands.set("key", "Hello, Redis11!");
        System.out.println(code);
        closeConnection();
    }

    @Test
    public void testString(){
        //RedisClient redisClient = RedisClient.create("redis://password@127.0.0.1:6379/0");
        initConnection();

        RedisCommands<String, String> syncCommands = connection.sync();

        for(int i=0;i<10000;i++) {
            String key = "SSO:User:StringKey:"+i;
            Assert.assertEquals("OK", syncCommands.set(key, UUID.randomUUID().toString()));
            syncCommands.expire(key,10);
            Long exist=1L;
            Assert.assertEquals(exist,syncCommands.exists(key));

            System.out.println(syncCommands.get(key));
        }

        //System.out.println(code);
        closeConnection();
    }

    @Test
    public void testHase1(){
        //RedisClient redisClient = RedisClient.create("redis://password@127.0.0.1:6379/0");
        initConnection();

        RedisHashCommands<String, String> syncHaseCommands =(RedisHashCommands) connection.sync();
        String key="MapKey1";
        Map<String,String> mapData=new HashMap<String, String>();
        mapData.put("1","1data1");
        mapData.put("2","2data2");
        syncHaseCommands.hset(key,mapData);

        mapData=syncHaseCommands.hgetall(key);
        Assert.assertEquals(2,mapData.size());

        //System.out.println(code);
        closeConnection();
    }
}
