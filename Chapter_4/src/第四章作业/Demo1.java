package 第四章作业;

import java.util.Scanner;

public class Demo1 {
	public static int Fibonacci(int n) {
		if(n < 2) {
			return 1;
		}
		else {
			return (Fibonacci(n - 2) + Fibonacci(n - 1));
		}
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		for(int i=0; i<num; i++) {
			System.out.print(Fibonacci(i) + " ");
		}
	}
}
