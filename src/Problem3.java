
public class Problem3 {
	public static void main (String [] args){
		long a = 600851475143L;
		int num = 2;
		StringBuilder myString = new StringBuilder();

		while (!(a == num) ){
			if (a==1){
				break;
			}
				if (checkPrime(num)){
					if (a % num == 0){
						myString.append(num + "x");
						a = a / num;
					}
					else{
						num = num + 1;
					}
				}
				else{
					num = num +1;
				}
			}
		
		System.out.println(myString.toString()+num);
		String [] myArray = (myString.toString()+num).split("x");
		int []numArray = new int[myArray.length];
		int i = 0;
		for (String m : myArray){
			numArray[i] = Integer.parseInt(m);
			i++;
		}
		int highnumber = 0;
		for (int number :numArray){
			if (number > highnumber){
				highnumber = number;
			}
		}
		System.out.println("highNumber:" +highnumber);

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
