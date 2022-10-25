package 第三章作业;

public class Dog {
	private String name;
	private String says;
	
	Dog(String Name, String Says){
		this.name = Name;
		this.says = Says;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSays() {
		return says;
	}
	
	public void setName(String Name) {
		this.name = Name;
	}
	
	public void setSays(String Says) {
		this.says = Says;
	}
	
	public void show() {
		System.out.println(name + "的叫声为" + says);
	}
}
