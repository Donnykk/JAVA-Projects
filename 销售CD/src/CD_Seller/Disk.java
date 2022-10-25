package CD_Seller;

public class Disk {
	private int price;
	private int id;
	private String name;
	private int num;

	public Disk(int price, int id, String name, int num) {
		super();
		this.price = price;
		this.id = id;
		this.name = name;
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "Disk [id=" + id + ", name=" + name + ", price=" + price + ", num=" + num + "]";
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
