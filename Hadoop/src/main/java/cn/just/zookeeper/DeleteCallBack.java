package cn.just.zookeeper;

import org.apache.zookeeper.AsyncCallback.VoidCallback;

public class DeleteCallBack implements VoidCallback{

	public void processResult(int rc, String path, Object ctx) {
		System.out.println("�����Ĳ���Ϊ��"+ctx);
	}

}
