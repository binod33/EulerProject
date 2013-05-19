
public class Problem1 {

	/**
	 * sum of multiples of 3 and 5 between 0 and 1000
	 * @param args
	 */
	public static void main(String[] args) {
		int sum  = 0;
		for (int i = 1; i < 1000; i++){
			if ((i % 3) == 0 || (i % 5) == 0){
				System.out.println(i + " is a multiple");
				sum = sum + i;
			}
		}
		System.out.println(sum);
	}

}
