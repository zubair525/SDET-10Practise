package GenericLib;

import java.util.Random;

public class RandomData {
	public int randomData() {
		Random r=new Random();
		int random = r.nextInt(1000);
		return random;
	}
}
