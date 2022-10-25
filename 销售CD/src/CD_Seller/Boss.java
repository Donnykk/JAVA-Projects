package CD_Seller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boss {
	private UserBook ub = new UserBook();
	private DiskBook db = new DiskBook();
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		Boss boss = new Boss();
		boss.begin();
	}

	public void begin() {
		while (true) {
			printMainMenu();
			int choice = readUserInput();
			switch (choice) {
			case 1:
				userManage();
				break;
			case 2:
				diskManage();
				break;
			case 3:
				returnDisk();
				break;
			case 4:
				borrow();
				break;
			case 5:
				return;
			}
		}
	}

	private void borrow() {
		try {
			System.out.println("please input userId");
			int userId = Integer.parseInt(in.readLine());
			System.out.println("please input diskId");
			int diskId = Integer.parseInt(in.readLine());
			User user = ub.findUser(userId);
			Disk disk = db.findDisk(diskId);
			int money = user.getMoney();
			int price = disk.getPrice();
			if (money < price) {
				System.out.println("There's not enough money!");
				return;
			} else {
				user.setMoney(money - price);
				user.getDiskBook().addDisk(disk);
				db.removeDisk(disk);
				System.out.println("Borrow Succeed!");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void returnDisk() {
		try {
			System.out.println("please input userId");
			int userId = Integer.parseInt(in.readLine());
			System.out.println("please input diskId");
			int diskId = Integer.parseInt(in.readLine());
			User user = ub.findUser(userId);
			Disk disk = user.getDiskBook().findDisk(diskId);
			user.getDiskBook().removeDisk(disk);
			db.addDisk(disk);
			System.out.println("Return succeed!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void diskManage() {
		while (true) {
			printDiskMenu();
			int choice = readUserInput();
			switch (choice) {
			case 1:
				importDisk();
				break;
			case 2:
				figure();
				break;
			case 5:
				return;
			}
		}
	}

	private void figure() {
		db.print();
	}

	private void importDisk() {
		try {
			System.out.println("Please input name");
			String name;
			name = in.readLine();
			System.out.println("please input id");
			int id = Integer.parseInt(in.readLine());
			System.out.println("money?");
			int money = Integer.parseInt(in.readLine());
			Disk disk = new Disk(money, id, name, 1);
			db.addDisk(disk);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void userManage() {
		while (true) {
			printUserMenu();
			int choice = readUserInput();
			switch (choice) {
			case 1:
				addUser();
				break;
			case 2:
				removeUser();
				break;
			case 3:
				findUser();
				break;
			case 5:
				return;
			}
		}
	}

	private void findUser() {
		System.out.println("Please input id:");
		try {
			int id = Integer.parseInt(in.readLine());
			User user = ub.findUser(id);
			System.out.println(user);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void removeUser() {
		System.out.println("Please input id:");
		try {
			int id = Integer.parseInt(in.readLine());
			ub.removeUser(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addUser() {
		try {
			System.out.println("please tell me username");
			String name = in.readLine();
			System.out.println("please input id");
			int id = Integer.parseInt(in.readLine());
			System.out.println("money?");
			int money = Integer.parseInt(in.readLine());
			DiskBook diskBook = new DiskBook();
			User user = new User(id, name, money, diskBook);
			ub.addUser(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printUserMenu() {
		System.out.println("1:add user");
		System.out.println("2:remove user");
		System.out.println("3:find user");
		System.out.println("5:quit");
	}

	private void printDiskMenu() {
		System.out.println("1:import disk");
		System.out.println("2:figure");
		System.out.println("5:quit");
	}

	private int readUserInput() {
		try {
			String line;
			line = in.readLine();
			return Integer.parseInt(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private void printMainMenu() {
		System.out.println("Please input a number:");
		System.out.println("1:User Manage");
		System.out.println("2:Disk Manage");
		System.out.println("3:return");
		System.out.println("4:borrow");
		System.out.println("5:quit");

	}
}
