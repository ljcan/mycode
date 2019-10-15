package cn.just.zookeeper;

import org.apache.hadoop.mapreduce.jobhistory.EventType;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class WatcherExample implements Watcher{
	

	public void process(WatchedEvent event) {
//		event = new WatchedEvent(EventType.TASK_STARTED,KeeperState.SyncConnected,"");
		event = new WatchedEvent(null);
	}

}
