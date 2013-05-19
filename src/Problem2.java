
public class Problem2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 1;
		int j = 2;
		System.out.println(i);
		System.out.println(j);

		int temp = i + j;
		int evenSum = j;
		while (temp < 4000000){
			System.out.println(temp);
			i = j;
			j = temp;	
			if ( (i+j) > 4000000){
				break;
			}
			temp = i + j;
				if (temp > 2 && (temp % 2 ) == 0){
					evenSum = evenSum + temp; 
				}
		}
		System.out.println("Even sum = " + evenSum);
	}

}
