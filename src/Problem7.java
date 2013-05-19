
public class Problem7 {

	/**
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
	 * What is the 10 001st prime number?
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int count = 1;
		int num = 2;
		while(count <= 10001){
			if (checkPrime(num)){
				System.out.println(count + " = " + num);
				count = count + 1;
				num = num + 1;
			}
			else{
				num = num + 1;
			}
		}
	}
	
	public static boolean checkPrime (int p){
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
