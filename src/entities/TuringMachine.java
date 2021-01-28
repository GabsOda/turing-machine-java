package entities;

import java.util.Set;

public class TuringMachine {
	private String name; 
	private Set<Character> alphabet;
	private Set<String> states;
	private String initialState;
	private Set<String> finalStates;
	private Set<Character> auxAlphabet;
	
	private char whiteChar;
	private char initialchar;
	
	private Set<Transition> transitions;
	
	public TuringMachine() {
	}

	public TuringMachine(String name, Set<Character> alphabet, Set<String> states, String initialState,
			Set<String> finalStates, Set<Character> auxAlphabet, char whiteChar, char initialchar, Set<Transition> transitions) {
		this.name = name;
		this.alphabet = alphabet;
		this.states = states;
		this.initialState = initialState;
		this.finalStates = finalStates;
		this.auxAlphabet = auxAlphabet;
		this.whiteChar = whiteChar;
		this.initialchar = initialchar;
		this.transitions = transitions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Character> getAlphabet() {
		return alphabet;
	}

	public Set<String> getStates() {
		return states;
	}

	public String getInitialState() {
		return initialState;
	}

	public void setInitialState(String initialState) {
		this.initialState = initialState;
	}

	public Set<String> getFinalStates() {
		return finalStates;
	}

	public Set<Character> getAuxAlphabet() {
		return auxAlphabet;
	}

	public char getWhiteChar() {
		return whiteChar;
	}

	public void setWhiteChar(char whiteChar) {
		this.whiteChar = whiteChar;
	}

	public char getInitialchar() {
		return initialchar;
	}

	public void setInitialchar(char initialchar) {
		this.initialchar = initialchar;
	}

	public Set<Transition> getTransitions() {
		return transitions;
	}

	public boolean isFinalState(String state) {
		for(String fs : finalStates) {
			if (fs.contains(state)) {
				return true;
			}
		}
		return false;
	}
	
	public Transition findTransition(String state, char c) {
		for(Transition transition : transitions) {
			if (transition.getFrom().equals(state) && transition.getRead() == c) {
				return transition;
			}
		}
		return null;
	}
	
	public void addTransition(Transition transition) {
		if(transition == null) {
			throw new TuringMachineException("Transition was null!");
		}
		transitions.add(transition);
	}
	
	public ProcessResult run(String tape, boolean silentMode) {
		if(tape == null) {
			throw new TuringMachineException("Tape was null");
		}
		ProcessResult result = new ProcessResult(tape, null, false, false);
		String state = initialState;
		
		if(tape.charAt(0) != initialchar) {
			tape = initialchar + tape;
		}
		if(tape.charAt(tape.length()-1) != whiteChar) {
			tape = ""+ tape + whiteChar;
		}
		
		char[] auxTape = tape.toCharArray();

		String resultTape = null;
		boolean stop = false;
		int i = 0;
		
		while(!stop) {
			char c = auxTape[i];
			
			if(silentMode) System.out.print("In State: " + state);
			
			if(isFinalState(state)) {
				if(silentMode) System.out.println(", this is the Final State!\n");
				resultTape = new String(auxTape);	
				result.setAccepted(true);
				result.setResult_tape(resultTape);
				break;
			}
			
			if(silentMode) System.out.println(" | read: " + c);

			Transition transition = findTransition(state, c);
			if(silentMode && transition != null) System.out.println(transition.printTransition());
				
			if(transition != null) {
				auxTape[i] = transition.getWrite();
				if(transition.getDirection() == 'D' || transition.getDirection() == 'R'){
					i++;
				}else if(transition.getDirection() == 'E' || transition.getDirection() == 'L') {
					i--;
					if(i < 0) {
						resultTape = new String(auxTape);
						result.setAccepted(false);
						result.setResult_tape(resultTape);
						break;
					}
				}
				if(silentMode) System.out.println("ResultTape: "+ new String(auxTape)+ "\n");
				
				state = transition.getTo();
				
			}else {
				resultTape = new String(auxTape);	
				result.setAccepted(false);
				result.setResult_tape(resultTape);
				break;
			}
			
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "TuringMachine [\n "
				+ "name: " + name
				+ ",\n alphabet: " + alphabet
				+ ",\n states: " + states
				+ ",\n initial State: " + initialState
				+ ",\n final States: " + finalStates 
				+ ",\n aux Alphabet: " + auxAlphabet 
				+ ",\n white Char: " + whiteChar 
				+ ",\n initial Char: " + initialchar 
				+ ",\n transitions:  " + transitions 
				+ "\n]";
	}
}
