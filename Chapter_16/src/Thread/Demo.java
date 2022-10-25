package Thread;

public class Demo {
	public static void main(String[] args) {
		MyThread my1 = new MyThread(1, 1000);
		MyThread my2 = new MyThread(1001, 2000);
		MyThread my3 = new MyThread(2001, 3000);
		my1.setPriority(10); // 设置优先级递减
		my2.setPriority(5);
		my3.setPriority(1);
		my1.start(); // 3线程同时查找
		my2.start();
		my3.start();
	}
}
