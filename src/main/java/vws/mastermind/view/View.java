package vws.mastermind.view;

public interface View {

	public void initGame();
	
	public void playRound(int roundNo);
	
	public void registerNewGuessListener(NewGuessListener listener);
	
	public void setFeedback(int noOfEverythingRight, int noOfRightCodeKey);
	
	public void showCode(int[] code);

	public void congratulate();

	public void gameOver();
	
}
