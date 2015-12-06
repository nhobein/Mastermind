package vws.mastermind.view;

import java.util.ArrayList;
import java.util.List;

public class NewGuessHandler {

	private List<NewGuessListener> listeners = new ArrayList<NewGuessListener>();

    protected void addListener(NewGuessListener listener) {
        listeners.add(listener);
    }

    protected void newGuess(int[] guess) {
        for (NewGuessListener listener : listeners)        	
            listener.newGuess(guess);
    }
	
}
