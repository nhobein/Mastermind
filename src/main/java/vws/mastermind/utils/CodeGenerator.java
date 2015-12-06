package vws.mastermind.utils;

import java.util.Random;
import vws.mastermind.shared.Constants;

public class CodeGenerator {

	public static int[] generateCode() {
		Random rand = new Random();
		int[] code = new int[Constants.CODE_LENGTH];
		for (int i = 0; i < Constants.CODE_LENGTH; i ++) {
			code[i] = rand.nextInt((Constants.MAX_VALUE - Constants.MIN_VALUE) + 1) + Constants.MIN_VALUE;
		}
		return code;
	}
	
}
