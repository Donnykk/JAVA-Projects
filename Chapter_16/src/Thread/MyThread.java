package Thread;

public class MyThread extends Thread {
	private int start = 0;
	private int end = 0;

	public MyThread(int start, int end) {
		// TODO 自动生成的构造函数存根
		this.start = start;
		this.end = end;
	}

	public boolean isSushu(int target) { // 判断是否为素数
		if (target == 1) {
			return false;
		} else if (target == 2) {
			return true;
		}
		for (int i = 2; i < target; i++) {
			if (target % i == 0) {
				return false;
			}
		}
		return true;
	}

	public void run() {
		for (int i = start; i <= end; i++) {
			if (isSushu(i)) {
				System.out.println(getName() + ":" + i);
			} else {
				continue;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
