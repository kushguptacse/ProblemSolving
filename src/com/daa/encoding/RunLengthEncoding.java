package com.daa.encoding;

/**
 * If we want to send data "AACCCCBBAADCCCCCCC" over Internet we will need 18
 * characters. but if we just calculate count of consecutive characters we can
 * save space. here, We will send "2A4C2B2A1D7C" which will just take 12
 * character.
 * 
 * This approach is called run length encoding. it is best suited for black and
 * white image. as there we will have either black or white values. i.e. two
 * possible values. as we are prefixing count with character. the more variety
 * of character we have and the less number of repetition we have. then in such
 * case we might send more then actual length string data over network
 *
 *
 * NOTE - for LZW see udemy for flow.
 */
public class RunLengthEncoding {

	public static void main(String[] args) {
		RunLengthEncoding obj = new RunLengthEncoding();
		String orignal = "AAABBA";
		System.out.println("orignal- " + orignal);
		String encodedStr = obj.encode(orignal);
		System.out.println("encoded- " + encodedStr);
		System.out.println("decoded- " + obj.decode(encodedStr));

		orignal = "AACCCCBBAADCCCCCCCCCCCD";
		System.out.println("orignal: " + orignal);
		encodedStr = obj.encode(orignal);
		System.out.println("encoded: " + encodedStr);
		System.out.println("decoded: " + obj.decode(encodedStr));

		orignal = "AX";
		System.out.println("orignal: " + orignal);
		encodedStr = obj.encode(orignal);
		System.out.println("encoded: " + encodedStr);
		System.out.println("decoded: " + obj.decode(encodedStr));

	}

	/**
	 * Encode the string to be sent over network
	 * 
	 * @param plainText
	 * @return encoded String
	 */
	public String encode(String plainText) {
		if (plainText == null || plainText.isEmpty()) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		int count = 1;
		for (int i = 0; i < plainText.length() - 1; i++) {
			if (plainText.charAt(i) == plainText.charAt(i + 1)) {
				count++;
			} else {
				result.append(count).append(plainText.charAt(i));
				count = 1;
			}
		}
		return result.append(count).append(plainText.charAt(plainText.length() - 1)).toString();
	}

	/**
	 * 
	 * @param encodedStr
	 * @return decoded string
	 */
	public String decode(String encodedStr) {
		if (encodedStr == null || encodedStr.isEmpty()) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		int count = 0;
		for (int i = 0; i < encodedStr.length(); i++) {
			char c = encodedStr.charAt(i);
			if (Character.isDigit(c)) {
				count = count * 10 + c - '0';
			} else {
				while (count > 0) {
					result.append(c);
					count--;
				}
			}
		}

		return result.toString();
	}

}
