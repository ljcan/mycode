package cn.just.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
/**
 * curator开源框架
 * 解决了原生api：
 * session超时重连
 * watcher重复注册
 * NodeExistsException异常
 * Fluent风格api规范
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
	 * 创建节点，可以递归创建
	 * @param curator
	 */
	public static void createNode(String path,byte[] data) {
		try {
			curator.create().creatingParentsIfNeeded()   //递归创建
//					.inBackground(callback)              //异步调用
					.withMode(CreateMode.PERSISTENT)	 //设置创建模式，如果是递归节点，设置的是临时节点，则只有叶子节点是临时节点，非叶子节点是持久节点
					.forPath(path, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除子节点
	 * 可以递归删除
	 * @param path
	 * @param version
	 */
	public static void deleteNode(String path,int version) {
		try {
			curator.delete()
					//确保被删除：当执行删除操作的时候，有可能在服务器端的操作成功了，但是在在返回客户端的时候
					//断开连接，会导致客户端没有接受到删除成功的通知
					.guaranteed()    			  
					.deletingChildrenIfNeeded()   //递归删除子节点
					.withVersion(version)
					.forPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 异步删除
	 * @param path
	 * @param data
	 */
	public static void deleteNodeSync(String path,int version) {
		try {
			curator.delete()
					.guaranteed()
					.deletingChildrenIfNeeded()
					.withVersion(version)
					.inBackground(new DeleteCallback())    //设置异步回调函数
					.forPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
