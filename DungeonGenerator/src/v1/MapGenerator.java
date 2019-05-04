package v1;
import java.util.Random;

public class MapGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Map(35, 35, 7);
	}

	
	public static int randomNum(int max, int min) {
		
		return new Random().nextInt(max + 1 - min) + min;
		
	}
}
