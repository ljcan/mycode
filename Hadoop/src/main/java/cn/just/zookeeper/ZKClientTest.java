package cn.just.zookeeper;

import org.I0Itec.zkclient.ZkClient;
/**
 * ��Դ�ͻ���ZkClient
 * �����ԭ��API��
 * session��ʱ�����Լ�Watcher����ע�ᣬ����API�Ŀ���
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
