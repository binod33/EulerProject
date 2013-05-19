
public class Problem4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int highest = 0;
		for (int i = 100; i <=999; i++){
			for (int j = 100 ; j <= 999; j++){
				int product = i * j;
				String number = product+"";
				int length = number.length();
				if (length % 2 == 0){
					int k = 0;
					boolean palindrome = true;
					while (k < (length/2)){
						if (number.charAt(k) != number.charAt(length-1-k)){
							palindrome  = false;
							break;
						}
						k = k+1;
					}
					if (!palindrome){
						continue;
					}
				}
				else {
					int k = 0;
					boolean palindrome = true;
					while (k <= (length/2)){
						if (number.charAt(k) != number.charAt(length-1-k)){
							palindrome  = false;
							break;
						}
						else {
						}
						k = k+1;

					}
					if (!palindrome){
						continue;
					}				
				}
				if (Integer.parseInt(number) > highest){
					highest = Integer.parseInt(number);
				}

			}

		}
		System.out.println("Highest palindrome from multiplying 2 three digit numbers is " + highest);

	}

}
