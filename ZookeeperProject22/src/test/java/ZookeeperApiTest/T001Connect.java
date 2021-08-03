package ZookeeperApiTest;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T001Connect implements Watcher {

    private static CountDownLatch countDownLatch=new CountDownLatch(1);

    @Test
    public void T001Connect_D1() throws IOException {
        String hostPort = "localhost:2181";
        String zpath = "/";
        List<String> zooChildren = new ArrayList<String>();
        ZooKeeper zk = new ZooKeeper(hostPort, 2000, null);
        if (zk != null) {
            try {
                //zk.exists()
                zk.create("/ZookeeperApiTest","".getBytes(StandardCharsets.UTF_8),  ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

                zooChildren = zk.getChildren(zpath, this);
                System.out.println("Znodes of '/': ");
                for (String child: zooChildren) {
                    //print the children
                    System.out.println(child);
                }

                countDownLatch.await();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
        //countDownLatch.countDown();
    }
}
