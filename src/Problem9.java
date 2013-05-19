
public class Problem9 {

	/**
	 * A Pythagorean triplet is a set of three natural numbers, a  b  c, for which,
	 * a2 + b2 = c2
	 * For example, 32 + 42 = 9 + 16 = 25 = 52.
	 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
	 * Find the product abc.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		for(long i = 1; i <= 998; i ++){
			for (long j =1; j<= 998; j++){
				for (long k = 1; k<= 998; k++){
					if ((i+j+k) != 1000){
						continue;
					}
					if ( i>=j || j>=k || i>=k){
						continue;
					}
					long iSquared = i*i;
					long jSquared = j*j;
					long kSquared = k*k;
					
					if ((iSquared + jSquared) != kSquared){
						continue;
					}
					System.out.println(i+" - "+j+" - " + k);
					System.out.println("Product = " + i*j*k);
				}
			}
		}
	}

}
