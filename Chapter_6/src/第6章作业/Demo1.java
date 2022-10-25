package µÚ6ÕÂ×÷Òµ;

public class Demo1 {
	public static void main(String[] args) {
		ConnectionManager conn = new ConnectionManager();
		for(int i=0; i<11; i++) {
			ConnectionManager.getNewConnection();
		}
	}
}
