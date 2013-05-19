
public class Problem6 {

	/**
	 * The sum of the squares of the first ten natural numbers is,
	 * 12 + 22 + ... + 102 = 385
	 * The square of the sum of the first ten natural numbers is,
	 * (1 + 2 + ... + 10)2 = 552 = 3025
	 * Hence the difference between the sum of the squares of the first 
	 * ten natural numbers and the square of the sum is 3025  385 = 2640.
	 * Find the difference between the sum of the squares of 
	 * the first one hundred natural numbers and the square of the sum.
	 * @param args
	 */
	public static void main(String[] args) {
		long start = 1;
		long end = 100;
		long sumOfSquares = sosq(start,end);
		long squareOfSum = sqos(start,end);
		
		System.out.println("The difference of them is = "+ (squareOfSum - sumOfSquares));
	}
	
	private static long sosq(long start, long end) {
		long sum = 0;
		for (long i = start; i<=end; i ++){
			sum = sum + (i*i);
		}
		return sum;
	}

	public static long sqos(long start, long end){
		long sum = 0;
		for (long i = start; i<=end; i ++){
			sum = sum + i;
		}
		return (sum*sum);
	}
	
	

}
