
public class Problem2 {

	/**
	 * By considering the terms in the Fibonacci sequence whose values 
	 * do not exceed four million, find the sum of the even-valued terms.
	 * @param args
	 */
	public static void main(String[] args) {
		Integer firstFibonacciTerm = 1;
		Integer secondFibonacciTerm = 2;
		final Integer termValueLimit = 4000000;
		Integer nextFibonacciTerm = 0;
		Integer evenSum = 0;
		System.out.println(firstFibonacciTerm);
		System.out.println(secondFibonacciTerm);

		if (isEven(firstFibonacciTerm)){
			evenSum = evenSum + firstFibonacciTerm; 
		}
		if (isEven(secondFibonacciTerm)){
			evenSum = evenSum + secondFibonacciTerm; 
		}
		while (nextFibonacciTerm < termValueLimit){
			nextFibonacciTerm = firstFibonacciTerm + secondFibonacciTerm;
			System.out.println(nextFibonacciTerm);

			if (isEven(nextFibonacciTerm)){
				evenSum = evenSum + nextFibonacciTerm; 
			}
			firstFibonacciTerm = secondFibonacciTerm;
			secondFibonacciTerm = nextFibonacciTerm;	
			if ( (firstFibonacciTerm + secondFibonacciTerm) > termValueLimit){
				break;
			}
		}
		System.out.println("Even sum = " + evenSum);
	}

	static boolean isEven(Integer fibonacciTwoTermSum) {
		final Integer two = 2;
		return fibonacciTwoTermSum % two == 0;
	}

}
