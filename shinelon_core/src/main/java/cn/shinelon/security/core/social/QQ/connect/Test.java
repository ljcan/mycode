package cn.shinelon.security.core.social.QQ.connect;

import java.util.LinkedList;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		LinkedList<Integer> ll=new LinkedList<Integer>();
		ll.add(1);
		ll.add(2);
		new a(ll);
		System.err.println(ll);
		scanner.close();
	}
}

class a{
	LinkedList<Integer> ll;
	public a(LinkedList<Integer> a) {
		this.ll=a;
	}
	void d() {
		ll.add(3);
	}
}