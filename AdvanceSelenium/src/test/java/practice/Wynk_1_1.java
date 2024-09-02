package practice;

public class Wynk_1_1 extends Wynk_1_0{

	public void previousSong() {
		System.out.println("Swipdown to previous song");
	}

	public void nextSong() {
		System.out.println("Swipup to next Song");
	}

	public static void main(String[] agrs) {
		Wynk_1_1 w1= new Wynk_1_1();
		w1.pausebtn();
		w1.playbtn();
		w1.previousSong();
		w1.nextSong();
		System.out.println(w1.getPrime(30));
	}

}
