package com.test.zookeeper;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @author trangle
 */
public class App {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // zookeeper测试代码
        ZookeeperTest dm = new ZookeeperTest();
        dm.createZKInstance();
        dm.ZKOperations();
        dm.ZKClose();
    }
}
