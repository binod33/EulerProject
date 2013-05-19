
public class Problem10 {

	/** 
	 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
	 * Find the sum of all the primes below two million.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long sum = 0;
		long num = 2;
		while(num < 2000000){
			if (checkPrime(num)){
				System.out.println("Prime num : " + num);
				sum = sum + num;
				num = num + 1;
			}
			else{
				num = num + 1;
			}
		}
		System.out.println("sum of the primes : " + sum);
		//Answer : = sum of the primes : 142913828922
	}
	
	public static boolean checkPrime (long p){
		if (p <=2){
			return true;
		}
		for (int i = 2; i < p; i++){
			if (p % i == 0){
				return false;
			}
		}
		return true;
	}

}
