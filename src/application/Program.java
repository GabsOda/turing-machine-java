package application;

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
		
		System.out.println("Enter de tape for read: ");
		String tape = sc.nextLine();
		
		ProcessResult pr = tm.run(tape);
		System.out.println(pr);
		
		
		
		sc.close();
	}
}
