
public class Problem5 {

	/**
	 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
	 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
	 * @param args
	 */
	public static void main(String[] args) {
		boolean found = false;
		int num  = 0;
		int i = 21;
		while (true){
			for (int j = 1; j<= 20; j++){
				if (!((i % j) == 0)){
					i = i + 1;
					found = false;
					break;
				}
				else {
					found = true;
					num = i;
				}
			}
			if (found){
				break;
			}
		}
		System.out.println(num);
		
	}

}
