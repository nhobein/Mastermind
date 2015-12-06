package vws.mastermind.utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import vws.mastermind.shared.Constants;

public class CodeChecker {

	public static int[] checkCode(int[] code, int[] guess) {
		int[] feedback = new int[2];
		Multiset<Integer> codeLeft = HashMultiset.create();
		for(int i = 0; i < Constants.CODE_LENGTH; i++) {
			codeLeft.add(code[i]);
		}
		int noOfEverythingRight = 0;
		int noOfRightCodeKey = 0;
		for(int i = 0; i < Constants.CODE_LENGTH; i++) {
			if (guess[i] == code[i]) {
				noOfEverythingRight++;
				codeLeft.remove(guess[i]);				
			} else if (codeLeft.contains(guess[i])) {
				noOfRightCodeKey++;
				codeLeft.remove(guess[i]);
			}
		}
		feedback[0] = noOfEverythingRight;
		feedback[1] = noOfRightCodeKey;
		return feedback;
	}
	
}
