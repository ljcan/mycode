package cn.just.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class WaterRegister {
	
	private ZooKeeper zk = null;
	
	public WaterRegister(String connectString, Watcher watcher) {
		try {
			zk = new ZooKeeper(connectString,10000,watcher);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testWatcheDisabled(String path) throws KeeperException, InterruptedException {
		WatcherExample watch = new WatcherExample();
		zk.getData(path, watch, null);
	}
	
	
	
	
	

}
