package cn.just.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
/**
 * curator��Դ���
 * �����ԭ��api��
 * session��ʱ����
 * watcher�ظ�ע��
 * NodeExistsException�쳣
 * Fluent���api�淶
 * @author shinelon
 *
 */
public class CuratorClientTest {
	
	static CuratorFramework curator= CuratorFrameworkFactory.builder()
			.connectString("")
			.sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(5000, 10000))
			.build();
	
	public static void main(String[] args) {
		
		
		
		
				
	}
	
	/**
	 * �����ڵ㣬���Եݹ鴴��
	 * @param curator
	 */
	public static void createNode(String path,byte[] data) {
		try {
			curator.create().creatingParentsIfNeeded()   //�ݹ鴴��
//					.inBackground(callback)              //�첽����
					.withMode(CreateMode.PERSISTENT)	 //���ô���ģʽ������ǵݹ�ڵ㣬���õ�����ʱ�ڵ㣬��ֻ��Ҷ�ӽڵ�����ʱ�ڵ㣬��Ҷ�ӽڵ��ǳ־ýڵ�
					.forPath(path, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ���ӽڵ�
	 * ���Եݹ�ɾ��
	 * @param path
	 * @param version
	 */
	public static void deleteNode(String path,int version) {
		try {
			curator.delete()
					//ȷ����ɾ������ִ��ɾ��������ʱ���п����ڷ������˵Ĳ����ɹ��ˣ��������ڷ��ؿͻ��˵�ʱ��
					//�Ͽ����ӣ��ᵼ�¿ͻ���û�н��ܵ�ɾ���ɹ���֪ͨ
					.guaranteed()    			  
					.deletingChildrenIfNeeded()   //�ݹ�ɾ���ӽڵ�
					.withVersion(version)
					.forPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �첽ɾ��
	 * @param path
	 * @param data
	 */
	public static void deleteNodeSync(String path,int version) {
		try {
			curator.delete()
					.guaranteed()
					.deletingChildrenIfNeeded()
					.withVersion(version)
					.inBackground(new DeleteCallback())    //�����첽�ص�����
					.forPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
