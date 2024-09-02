package practice;

public class Wynk_1_0 {

	private String prime =null;

	public void previousSong() {
		System.out.println("Go to previous song");
	}

	public void nextSong() {
		System.out.println("Got to next Song");
	}

	public void pausebtn() {
		System.out.println("Pause the song");
	}

	public void playbtn() {
		System.out.println("Play the song");
	}

	public String getPrime(int a) {
		
		if(a==30) {
			prime="PrimeSongActivated";
		}else {
			prime="Enter valid amount";
		}
		return prime;
	}
}
