package 第五章作业;

import java.util.Scanner;

public class Demo2 {
	public static void showString(String... s) {
		for (String i : s) {
			System.out.print(i + ",");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] s = new String[5];
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			s[i] = sc.nextLine();
		}
		showString(s);
	}
}
