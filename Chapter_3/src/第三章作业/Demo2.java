package 第三章作业;

public class Demo2 {
	static void Tests(String p, String q) {
		if(p == q) {
			System.out.println("p == q");
		}
		else {
			System.out.println("p != q");
		}
		
		if(p != q) {
			System.out.println("p != q");
		}
		else {
			System.out.println("p == q");
		}
		
		if(p.equals(q)) {
			System.out.println("p == q");
		}
		else {
			System.out.println("p != q");
		}
	}	
	
	public static void main(String[] args) {
		String s1 = "Hello";
		String s2 = "hello";
		String s3 = "hello";
		Tests(s1, s2);
		Tests(s2, s3);
	}
}
