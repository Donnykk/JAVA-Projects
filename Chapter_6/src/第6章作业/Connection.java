package µÚ6ÕÂ×÷Òµ;

class Connection {
	private static int count = 0;
	private Connection() {
		System.out.println("Connection(" + count + ")");
	};
	static Connection makeNewConnection() {
		count++;
		return new Connection();
	}
}
