package cn.just.zookeeper;

import java.security.NoSuchAlgorithmException;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

public class DigestTest {
	public static void main(String[] args) {
		try {
			System.out.println(DigestAuthenticationProvider.generateDigest("shinelon:123456"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
