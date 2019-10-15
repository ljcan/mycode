package cn.just.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;

public class DeleteCallback implements BackgroundCallback{

	public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
		System.out.println(event.getPath()+",data="+event.getData());
	}

}
