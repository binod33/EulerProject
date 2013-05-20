/**
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * @author Binod KC
 * @author Erik Johnson
 *
 */
public class Problem4 {

	public static void main(String[] args) {
		final Integer largestPalindrome = findLargestPalindrome();
		System.out.println("Largest palindrome from multiplying 2 three digit numbers is " + largestPalindrome);
	}

	private static Integer findLargestPalindrome() {
		final Integer smallestThreeDigitNumber = 100;
		final Integer largestThreeDigitNumber = 999;
		Integer largestPalindrome = 0;
		for (Integer firstThreeDigitInt = smallestThreeDigitNumber; firstThreeDigitInt <= largestThreeDigitNumber; firstThreeDigitInt++) {
			for (Integer secondThreeDigitInt = smallestThreeDigitNumber; secondThreeDigitInt <= largestThreeDigitNumber; secondThreeDigitInt++){
				Integer product = firstThreeDigitInt * secondThreeDigitInt;
				if (!isPalindrome(product)){
					continue;
				}
				else if (product > largestPalindrome){
					largestPalindrome = product;
				}
			}
		}
		return largestPalindrome;
	}

	private static Boolean isPalindrome(Integer possiblePalindrome) {
		final String productString = Integer.toString(possiblePalindrome);
		final Integer productLength = productString.length();
		final Integer lastProductChar = productLength-1;
		final Double middleOfProduct = productLength/2.0;
		Integer characterIndex = 0;
		Boolean palindrome = true;
		Character leftChar;
		Character rightChar;
		while (characterIndex <= middleOfProduct) {
			leftChar = productString.charAt(characterIndex);
			rightChar = productString.charAt(lastProductChar-characterIndex);
			if (!leftChar.equals(rightChar)){
				palindrome  = false;
				break;
			}
			characterIndex++;
		}
		return palindrome;
	}

}
