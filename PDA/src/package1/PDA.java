package package1;

public class PDA {

	int stateNum;
	int varNum;
	int finalStateNum;
	String[] states;
	String startState;
	String[] varaibles;
	String[][] transTable;
	String[][] givenStrings;

	
	
	public PDA(int stateNum, int varNum, int finalStateNum, String[] states, String startState, String[] varaibles, String[][] transTable,
			String[][] givenStrings) {

		this.stateNum = stateNum;
		this.varNum = varNum;
		this.finalStateNum = finalStateNum;
		this.states = states;
		this.startState = startState;
		this.varaibles = varaibles;
		this.transTable = transTable;
		this.givenStrings = givenStrings;
	}
	
	
	
	
}
