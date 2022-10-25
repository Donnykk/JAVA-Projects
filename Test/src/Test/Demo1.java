package Test;

class Rodent {
	public void show() {
		System.out.println("I'm a redent!");
	}
}

class Mouse extends Rodent {
	@Override
	public void show() {
		System.out.println("I'm a mouse!");
	}
}

class Gerbil extends Rodent {
	@Override
	public void show() {
		System.out.println("I'm a gerbil!");
	}
}

class Hamster extends Rodent {
	@Override
	public void show() {
		System.out.println("I'm a hamster!");
	}
}


public class Demo1 {
	public static void main(String[] args) {
		Mouse m = new Mouse();
		Gerbil g = new Gerbil();
		Hamster h = new Hamster();
		Rodent rodents[] = {m, g, h};
		for(Rodent r : rodents) {
			r.show();
		}
	}
}
