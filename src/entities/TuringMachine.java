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
	
	public ProcessResult run(String tape) {
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

			if(isFinalState(state)) {
				resultTape = auxTape.toString();
				result.setAccepted(true);
				result.setResult_tape(auxTape.toString());
				break;
			}
			
			Transition transition = findTransition(state, c);;
				
			if(transition != null) {
				auxTape[i] = transition.getWrite();
				if(transition.getDirection() == 'D'){
					i++;
				}else if(transition.getDirection() == 'E') {
					i--;
					if(i < 0) {
						resultTape = auxTape.toString();
						result.setAccepted(false);
						result.setResult_tape(resultTape);
						break;
					}
				}
				state = transition.getTo();
				
			}else {
				resultTape = auxTape.toString();
				result.setAccepted(false);
				result.setResult_tape(resultTape);
				break;
			}
			
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "TuringMachine [\n name=" + name + ",\n alphabet=" + alphabet + ",\n states=" + states + ",\n initialState="
				+ initialState + ",\n finalStates=" + finalStates + ",\n auxAlphabet=" + auxAlphabet + ",\n whiteChar="
				+ whiteChar + ",\n initialchar=" + initialchar + "\n transitions= " + transitions + "\n]";
	}

	
	
	
}
