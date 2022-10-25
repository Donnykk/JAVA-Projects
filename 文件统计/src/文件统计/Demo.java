package 文件统计;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {
	public static long fileNum = 0;
	public static long fileSize = 0;
	public static long dirNum = -1;

	public static void showFile(File f) {
		if (f.isDirectory() == true) {
			dirNum++;
			File[] files = f.listFiles();
			if (files == null) {
				return;
			}
			for (File f1 : files) {
				showFile(f1);
			}
		} else {
			fileNum++;
			fileSize += f.length();
		}
	}

	public static void main(String[] agrs) throws IOException {
		System.out.println("请输入要统计的磁盘符：");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String rootDir = br.readLine();
		File f1 = new File(rootDir);
		showFile(f1);
		System.out.println("磁盘中文件个数为：" + fileNum);
		System.out.println("磁盘中文件夹个数为：" + dirNum);
		System.out.println("磁盘中文件总大小为：" + fileSize + "Bytes");
	}
}
