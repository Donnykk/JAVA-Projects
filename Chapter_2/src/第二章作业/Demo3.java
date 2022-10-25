package 第二章作业;

public class Demo3 {
	public static void main(String[] args) {
		AllTheColorsOfTheRainbow allTheColors = new AllTheColorsOfTheRainbow();
		System.out.println(allTheColors.anIntegerRepresentingColors);
		allTheColors.changeTheHueOfTheColor(6);
		System.out.println(allTheColors.anIntegerRepresentingColors);	
	}
}
