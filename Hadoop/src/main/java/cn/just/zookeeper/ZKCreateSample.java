package cn.just.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;


public class ZKCreateSample implements Watcher{
	
	private static CountDownLatch connectedSamphore = new CountDownLatch(1);

	public void process(WatchedEvent event) {
		System.out.println("receiver event = "+event);
		if(KeeperState.SyncConnected==event.getState()) {
			connectedSamphore.countDown();
		}
	}
	
	/**
	 * �����ڵ�
	 * @param zk
	 */
	public static void creatZKData(ZooKeeper zk) {
		List<ACL> list = new ArrayList<ACL>();
		//username:password������ʹ��SHA-1��BASE64���ܺ�Ľ��
		//DigestAuthenticationProvider.generateDigest("shinelon:123456")
		Id digestId = new Id("digest","shinelon:sp96SmHNVrfuSv3ViP6Tw7Z/2i4=");
		list.add(new ACL(Perms.ALL, digestId));
		try {
			zk.create("/javaclient4/test1", "111".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("�����ɹ�");
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ͬ��ɾ���ڵ�
	 * @param zk
	 */
	public static void deleteZKNode(ZooKeeper zk,String path,int version) {
		try {
			zk.delete(path, version);
			System.out.println("ɾ���ɹ�");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �첽ɾ��һ���ڵ�
	 * @param zk
	 * @param path
	 * @param version
	 * @param ctx
	 */
	public static void deleteZKNodeSync(ZooKeeper zk,String path,int version,Object ctx) {
		zk.delete(path, version, new DeleteCallBack(), ctx);
		//�����첽���ã������������н���֮�󣬿���û�з���ִ�н���ͶϿ����ӣ������Ҫ�ȴ��������
		try {
			Thread.sleep(2000);
			System.out.println("�첽ɾ���ڵ�ɹ�");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ�ӽڵ�
	 * @param zk
	 * @param path
	 * @param watch
	 */
	public static void getChildrenList(ZooKeeper zk,String path,boolean watch) {
		try {
			List<String> list = zk.getChildren(path, watch);
			for(String child:list) {
				System.out.println("�ӽڵ㣺"+child);
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ�ڵ������
	 * @param zk
	 * @param watch
	 * @param stat
	 */
	public static void getNodeData(ZooKeeper zk,String path , Boolean watch,Stat stat) {
		try {
			String data = Bytes.toString(zk.getData(path, watch, stat));
			System.out.println(path+"�ڵ�����Ϊ��"+data);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �޸Ľڵ�����
	 * @param zk
	 * @param path
	 * @param data
	 * @param version
	 */
	public static void setNodeDate(ZooKeeper zk,String path,byte[] data,int version) {
		try {
			zk.setData(path, data, version);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		ZooKeeper zk = new ZooKeeper("192.168.217.113:2181", 6000, new ZKCreateSample());
		System.out.println("begin state="+zk.getState());
		try {
			//�ȴ��ͻ��������˽����Ự�첽����
			connectedSamphore.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end state="+zk.getState());
		
		//����һ���ӽڵ�
//		creatZKData(zk);
		
//		deleteZKNode(zk,"/javaclient3",0);
		
//		deleteZKNodeSync(zk,"/javaclient2",0,"ɾ��һ���ڵ�");
		
//		getChildrenList(zk,"/javaclient4",false);
		
//		getNodeData(zk,"/javaclient4/test1",true,new Stat());
		
		setNodeDate(zk, "/javaclient4/test1","234".getBytes(),0);
		
		//�ڵ��Ƿ����
//		zk.exists(path, watch);
		
	}

}
