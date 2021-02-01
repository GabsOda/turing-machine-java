package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import entities.ProcessResult;
import entities.Transition;
import entities.TuringMachine;
import entities.TuringMachineException;

public class TuringMachineServices {

	public static TuringMachine FileReaderTxt(String path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			// name
			String[] fields = line.split("= ");
			String configName = fields[1];
			//System.out.println(configName);

			// alphabet
			line = br.readLine();
			fields = line.split(",");
			Set<Character> alphabet = new TreeSet<>();
			alphabet.add(fields[0].charAt(fields[0].length() - 1));
			for (int i = 1; i < fields.length; i++) {
				alphabet.add(fields[i].charAt(0));
			}
			/*
			for(char c : alphabet) {
				System.out.println(c);
			}
			*/
			
			// states
			line = br.readLine();
			fields = line.split(",");
			Set<String> states = new TreeSet<>();
			String auxSplitState = "" + fields[0].charAt(fields[0].length() - 2) + fields[0].charAt(fields[0].length() - 1);
			states.add(auxSplitState);
			for (int i = 1; i < fields.length; i++) {
				auxSplitState = "" + fields[i].charAt(0) + fields[i].charAt(1);
				states.add(auxSplitState);
			}
			/*
			for(String str : states) {
				System.out.println(str);
			} 
			*/	

			// initialState
			line = br.readLine();
			fields = line.split("= ");
			String initialState = fields[1];
			//System.out.println(initialState);

			// finalStates
			line = br.readLine();
			String[] auxStrFinalStates = line.split(" ");
			Set<String> finalStates = new TreeSet<>();				
			fields = auxStrFinalStates[2].split(",");
			String auxSplitFinalStates = "" + fields[0].charAt(1) + fields[0].charAt(2);
			finalStates.add(auxSplitFinalStates);
			for(int i = 1; i < fields.length; i++) {
				auxSplitFinalStates = "" + fields[i].charAt(0) + fields[i].charAt(1);
				finalStates.add(auxSplitFinalStates);
			}
		

			// auxAlphabet
			line = br.readLine();
			fields = line.split(",");
			Set<Character> auxAlphabet = new TreeSet<>();
			auxAlphabet.add(fields[0].charAt(fields[0].length() - 1));
			for (int i = 1; i < fields.length; i++) {
				auxAlphabet.add(fields[i].charAt(0));
			}
			/*
			for(char c : auxAlphabet) {
				System.out.println(c);
			}*/

			// WhiteChar
			line = br.readLine();
			fields = line.split("= ");
			char whiteChar = fields[1].charAt(0);
			//System.out.println(whiteChar);

			// InitialChar
			line = br.readLine();
			fields = line.split("= ");
			char InitialChar = fields[1].charAt(0);
			//System.out.println(InitialChar);

			line = br.readLine();
			//System.out.println(line);
			
			//transitions
			line = br.readLine();
			Set<Transition> transitions = new TreeSet<>();
			int i = 0;
			while (line != null) {
				fields = line.split(":");
				
				String[] transitionStates = fields[0].split(",");		
				String transitionFrom = transitionStates[0].substring(1);
				String transitionTo = transitionStates[1].substring(0, transitionStates[1].length() - 1);
				
				String[] transitionsChars = fields[1].split(",");			
				char transitionRead = transitionsChars[0].substring(1).charAt(0);	
				char transitionWrite = transitionsChars[1].charAt(0);
				char transitionDirection = transitionsChars[2].substring(0, transitionsChars[2].length() -1).charAt(0);
				
				Transition aux = new Transition(i, transitionFrom, transitionTo, transitionRead, transitionWrite, transitionDirection);
				transitions.add(aux);
				
				line = br.readLine();		
				i++;
			}
			/*
			for(Transition tran : transitions) {
				System.out.println(tran);
			}
			*/
			TuringMachine tm = new TuringMachine(configName, alphabet, states, initialState, finalStates, auxAlphabet, whiteChar, InitialChar, transitions);
			
			return tm;
		} catch (IOException io) {
			throw new TuringMachineException("Error read file");
		}
	}
	
	
	public static boolean outputWriter(List<ProcessResult> list, String path, boolean append) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, append))){
			for(ProcessResult result: list) {
				bw.write(result.toString());
				bw.newLine();
			}
			return true;
		}catch(IOException e) {
			return false;
		}
	}
	
}
