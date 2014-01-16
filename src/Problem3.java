import java.util.ArrayList;

/**
 * What is the largest prime factor of the number 600851475143?
 * @author Binod KC
 * @author Erik Johnson
 *
 */
public class Problem3 {
	
	final static Long factorThisNumber = 600851475143L;
	
	public static void main (String [] args) {
		StringBuilder factorString = new StringBuilder();
		ArrayList<Long> factors = new ArrayList<Long>();
		findAllFactors(factorString, factors);
		System.out.println("Factors of " + factorThisNumber + ": " + factorString.toString());
		Long largestPrimeFactor = findLargestFactor(factors);
		System.out.println("Largest prime factor: " + largestPrimeFactor);
	}

	private static Long findLargestFactor(ArrayList<Long> factors) {
		Long largestPrimeFactor = 0L;
		for (Long factor : factors){
			if (factor > largestPrimeFactor) {
				largestPrimeFactor = factor;
			}
		}
		return largestPrimeFactor;
	}

	private static void findAllFactors(StringBuilder factorString, ArrayList<Long> factors) {
		Long factor = factorThisNumber;
		Long smallFactor = 2L;
		while (smallFactor < factor) {
			if (checkPrime(smallFactor)) {
				if (checkFactor(smallFactor)) {
					factors.add(smallFactor);
					factorString.append(smallFactor + "x");
					factor = factor / smallFactor;
				}
			}
			smallFactor++;
		}
		factors.add(smallFactor);
		factorString.append(smallFactor);
	}

	private static Boolean checkPrime(Long possiblePrime) {
		Integer lowestEvenNumber = 2;
		Integer nothing = 0;
		if (possiblePrime == lowestEvenNumber){
			return true;
		}
		for (Integer iterator = lowestEvenNumber; iterator < possiblePrime; iterator++){
			if (possiblePrime % iterator == nothing){
				return false;
			}
		}
		return true;
	}

	private static Boolean checkFactor(Long possibleFactor) {
		Boolean isFactor;
		Integer nothing = 0;
		if (factorThisNumber % possibleFactor == nothing) {
			isFactor = true;
		}
		else {
			isFactor = false;
		}
		return isFactor;
	}

}
