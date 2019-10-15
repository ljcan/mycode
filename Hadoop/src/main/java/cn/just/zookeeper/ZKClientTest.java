package cn.just.zookeeper;

import org.I0Itec.zkclient.ZkClient;
/**
 * 开源客户端ZkClient
 * 解决了原生API：
 * session超时重连以及Watcher反复注册，简化了API的开发
 * @author shinelon
 *
 */
public class ZKClientTest {
	private ZkClient zkClient = null;
	public ZKClientTest() {
		this.zkClient = new ZkClient("", 5000);
	}
	
	public void createNode(String path,Object data) {
		zkClient.createPersistent(path, data);
	}
	
	public static void main(String[] args) {
		ZKClientTest test = new ZKClientTest();
		test.createNode("", null);
	}

}
