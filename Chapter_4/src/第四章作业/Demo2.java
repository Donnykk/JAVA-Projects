package 第四章作业;

public class Demo2 {
	public static int combine(int m, int n) {
		return (m * 10 + n);
	} 
	
	public static boolean Vampire(int n) {
		int firstNum = n / 1000;
		int secondNum = n % 1000 /100;
		int thirdNum = n % 100 / 10;
		int lastNum = n % 10;
		if(combine(firstNum, secondNum) * combine(thirdNum, lastNum) == n) {
			return true;
		}
		else if(combine(secondNum, firstNum) * combine(thirdNum, lastNum) == n) {
			return true;
		}
		else if(combine(firstNum, secondNum) * combine(lastNum, thirdNum) == n) {
			return true;
		}
		else if(combine(secondNum, firstNum) * combine(lastNum, thirdNum) == n) {
			return true;
		}
		else if(combine(firstNum, thirdNum) * combine(secondNum, lastNum) == n) {
			return true;
		}
		else if(combine(thirdNum, firstNum) * combine(secondNum, lastNum) == n) {
			return true;
		}
		else if(combine(firstNum, thirdNum) * combine(lastNum, secondNum) == n) {
			return true;
		}
		else if(combine(thirdNum, firstNum) * combine(lastNum, secondNum) == n) {
			return true;
		}
		else if(combine(firstNum, lastNum) * combine(thirdNum, secondNum) == n) {
			return true;
		}
		else if(combine(lastNum, firstNum) * combine(thirdNum, secondNum) == n) {
			return true;
		}
		else if(combine(firstNum, lastNum) * combine(secondNum, thirdNum) == n) {
			return true;
		}
		else if(combine(lastNum, firstNum) * combine(secondNum, thirdNum) == n) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void main(String args[]) {
		for(int i=1000; i<10000; i++) {
			if(Vampire(i)) {
				System.out.print(i + " ");
			}
		}
	}

}
