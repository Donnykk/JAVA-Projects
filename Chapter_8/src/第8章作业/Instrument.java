package µÚ8ÕÂ×÷Òµ;

public class Instrument {
	public void play() {
		check();
		System.out.println("Succeed!");
	}
	
	public void check() {
		System.out.println("Dudududu~~~");
	}
	
	public static void main(String[] args) {
		Instrument g = new Guita();
		g.play();
	}
}

class Guita extends Instrument {
	@Override
	public void check() {
		System.out.println("DengDengDengDeng");
	}
}
