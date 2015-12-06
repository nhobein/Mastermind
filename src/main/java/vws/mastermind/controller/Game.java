package vws.mastermind.controller;

import vws.mastermind.shared.Constants;
import vws.mastermind.utils.CodeChecker;
import vws.mastermind.utils.CodeGenerator;
import vws.mastermind.view.ConsoleView;
import vws.mastermind.view.NewGuessListener;
import vws.mastermind.view.View;

public class Game implements NewGuessListener {

	private View view;
	private int[] code;
	private int roundNo;
	
	public Game() {
		view = new ConsoleView();
		view.registerNewGuessListener(this);
		code = CodeGenerator.generateCode();
		start();
	}
	
	private void start() {
		roundNo = 1;
		view.initGame();	
		view.playRound(roundNo);
	}

	@Override
	public void newGuess(int[] guess) {
		roundNo++;
		int[] feedback = CodeChecker.checkCode(code,  guess);
		view.setFeedback(feedback[0], feedback[1]);
		if(feedback[0] == 4) {
			view.congratulate();
			view.showCode(code);
		} else if (roundNo == Constants.MAX_ROUNDS) {
			view.gameOver();
			view.showCode(code);
		} else {
			view.playRound(roundNo);
		}
	}

}
