package vws.mastermind.view;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import vws.mastermind.shared.Constants;

public class ConsoleView implements View {
	
private static Scanner scanner;

private NewGuessHandler eventHandler;

private BiMap<Character, Integer> codeKeys = HashBiMap.create();


public ConsoleView() {
	initView();
}

private void initView() {
	
	//init EventHandler
	eventHandler = new NewGuessHandler();
	//init scanner
	scanner = new Scanner(System.in);
	
	//init Mapping int -> char
	codeKeys.put('A', 0);
	codeKeys.put('B', 1);
	codeKeys.put('C', 2);
	codeKeys.put('D', 3);
	codeKeys.put('E', 4);
	codeKeys.put('F', 5);
	
	//init console output
	System.out.println("OoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoO");
	System.out.println("8                                                                 8");
	System.out.println("O                       M A S T E R M I N D                       O");
	System.out.println("8                                                                 8");
	System.out.println("OoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoO");
	System.out.println("");
	
}

@Override
public void initGame() {
	System.out.println("Neues Spiel:");
	System.out.println("");
	System.out.println("OoOoOoOoOoOoOoOoO");
	System.out.println("| ? | ? | ? | ? |");
	System.out.println("OoOoOoOoOoOoOoOoO");
	System.out.println("");
	System.out.println("Bitte geben Sie Ihren Tipp ein! Gesucht wird eine Kombination aus vier Buchstaben A-F, Buchstaben können mehrfach vorkommen.");
}

@Override
public void playRound(int roundNo) {
	int[] guess = new int[Constants.CODE_LENGTH];		
	System.out.print(roundNo + " || ");
	String input = scanner.nextLine();
	input = input.toUpperCase();
	if (validate(input)) {
		//generate intvalues for guess		
		for (int j = 0; j < Constants.CODE_LENGTH; j++) {
			char codekey = input.charAt(j);
			guess[j] = codeKeys.get(codekey);
		}
	} else {
		System.out.println("Falsche Eingabe: Bitte geben Sie eine Folge von vier Buchstaben der Buchstaben A-F ein.");
		playRound(roundNo);
	}
	eventHandler.newGuess(guess);
}

private boolean validate(String input) {
	return Pattern.matches("[A-F]{4}", input);
}

@Override
public void registerNewGuessListener(NewGuessListener listener) {
	eventHandler.addListener(listener);	
}

@Override
public void setFeedback(int noOfEverythingRight, int noOfRightCodeKey) {
	System.out.print("          | ");
	for(int i = 0; i < noOfEverythingRight; i++) {
		System.out.print("+");
	}
	for(int i = 0; i < noOfRightCodeKey; i++) {
		System.out.print("-");
	}
	for(int i = 0; i < (Constants.CODE_LENGTH - (noOfEverythingRight + noOfRightCodeKey)); i++) {
		System.out.print(" ");
	}
	System.out.println(" ||");
}

@Override
public void showCode(int[] code) {
	BiMap<Integer, Character> inverseMap = codeKeys.inverse();
	System.out.println("The right code was: " + inverseMap.get(code[0]) +  inverseMap.get(code[1]) + inverseMap.get(code[2])+ inverseMap.get(code[3]));
}

@Override
public void congratulate() {
	System.out.println("YOU WIN!");
}

@Override
public void gameOver() {
	System.out.println("GAME OVER :(");
	
}

}
