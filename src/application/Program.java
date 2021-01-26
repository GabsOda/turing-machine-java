package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.ProcessResult;
import entities.TuringMachine;
import services.TuringMachineServices;

public class Program {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the file path of the turing machine: ");
		String turingMachinePath = sc.nextLine();
		
		TuringMachine tm = TuringMachineServices.FileReaderTxt(turingMachinePath);
		System.out.println(tm);
		
		System.out.println("Enter the file path of the input: ");
		String inputPath = sc.nextLine();
		
		boolean silentMode;
		System.out.print("show transitions? (y, n): ");
		char silentModeConfig = sc.next().charAt(0);
		if(silentModeConfig == 'y') {
			silentMode = true; 
		}else {
			silentMode = false;
		}
		System.out.println();
		
		List<ProcessResult> results = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(inputPath))){
			 
			String line = br.readLine();
			while(line != null) {
				ProcessResult aux = tm.run(line, silentMode);
				
				System.out.println(aux);
				System.out.println();
				
				results.add(aux);
				
				line = br.readLine();
			}
			
		}catch (IOException io) {
			System.out.println("Error to read the input file");
		}
		
		sc.close();
	}
}
