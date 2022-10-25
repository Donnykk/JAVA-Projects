package CD_Seller;

public class UserBook {
	private User[] data = new User[10000];

	public void addUser(User u) {
		data[u.getId()] = u;
	}

	public User findUser(int id) {
		return data[id];
	}

	public void removeUser(int id) {
		data[id] = null;
	}

	public void print() {
		for(User user : data) {
			System.out.println(user);
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (User user : data) {
			result += user + "\n";
		}
		return result;
	}
}
