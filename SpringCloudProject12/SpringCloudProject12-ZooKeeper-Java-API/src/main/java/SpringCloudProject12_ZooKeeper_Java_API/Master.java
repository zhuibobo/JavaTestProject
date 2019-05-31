package SpringCloudProject12_ZooKeeper_Java_API;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import java.io.IOException;
import java.util.Random;

public class Master implements Watcher {

    private ZooKeeper zk;
    private String serviceId = Integer.toString(new Random().nextInt());
    private boolean isLeader = false;

    private void startZk() throws IOException {
        zk = new ZooKeeper("localhost:2181", 5000, this);
    }

    private void stopZk() throws InterruptedException {
        zk.close();
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("event: " + watchedEvent);
    }

    public static void main(String[] args) throws Exception {
        Master master = new Master();
        master.startZk();

        master.runForMaster();

        System.out.println("serviceId: " + master.serviceId);

        if (master.isLeader) {
            System.out.println("master");
            Thread.sleep(10000);
        } else {
            System.out.println("not master");
        }

        master.stopZk();
    }

    private boolean checkMaster() throws InterruptedException {
        while (true) {
            try {
                Stat stat = new Stat();
                byte data[] = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serviceId);
                return true;
            } catch (KeeperException.NoNodeException e) {
                return false;
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    private void runForMaster() throws InterruptedException {
        while (true) {
            try {
                zk.create("/master", serviceId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (KeeperException.NodeExistsException e) {
                isLeader = false;
                break;
            } catch (KeeperException e) {
                e.printStackTrace();
            }
            if (checkMaster()) {
                break;
            }
        }
    }
}
