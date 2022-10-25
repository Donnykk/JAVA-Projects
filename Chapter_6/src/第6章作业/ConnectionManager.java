package µÚ6ÕÂ×÷Òµ;

public class ConnectionManager {
	static int num = 10;
    static Connection[] con = new Connection[10];
    ConnectionManager(){
	   for(Connection c : con) {
			c = Connection.makeNewConnection();
		}
    }
    public static Connection getNewConnection() {
    	if (num > 0) {
    		num--;
    		return con[num];
    	}
    	else {
    		System.out.println("Empty!");
    		return null;
    	}
    }
}
