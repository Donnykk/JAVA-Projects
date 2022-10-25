package 第二章作业;

public class Demo2 {
	public static void main(String[] args) {
		//创建StaticDemo类的多个对象
		StaticDemo s1 = new StaticDemo();
		StaticDemo s2 = new StaticDemo();
		StaticDemo s3 = new StaticDemo();
		StaticDemo s4 = new StaticDemo(); 
		
		//测试各类中的i是否共用一个储存空间
		s1.test();
		s2.test();
		s3.test();
		s4.test();
	}
}
