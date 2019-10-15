package cn.shinelon.test;

public class TestInit {
	public static void main(String[] args) {
		System.out.println("name="+SuperTest.name);
		SuperTest[] test=new SuperTest[10];		//通过数组定义来引用类，不会触发此类的初始化
	}
}
class SonTest extends SuperTest{
	static {
		System.out.println("子类被加载");
	}
}
 class SuperTest{
	static {
		System.out.println("父类被加载");
	}
	public static final String name="子类";		//final修饰的常量被调用时不会初始化该类，由于常量传播
												//优先，此类已经存储到NotInitialization类的常量池中。
}
