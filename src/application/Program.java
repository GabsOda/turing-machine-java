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
		System.out.print("Show transitions? (y, n): ");
		char silentModeConfig = sc.next().charAt(0);
		if (silentModeConfig == 'y') {
			silentMode = true;
		} else {
			silentMode = false;
		}
		System.out.println();

		List<ProcessResult> results = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
			String line = br.readLine();
			while (line != null) {
				ProcessResult aux = tm.run(line, silentMode);
				results.add(aux);

				line = br.readLine();
			}

		} catch (IOException io) {
			System.out.println("Error to read the input file");
		}

		String[] path = inputPath.split("input");
		String outputPath = path[0] + "output" + path[1];

		
		System.out.println();
		boolean appendWrite;
		System.out.print("Rewrite the output file? (y, n): ");
		char appendWriterChar = sc.next().charAt(0);
		if(appendWriterChar == 'y') {
			appendWrite = false;
		}else {
			appendWrite = true;
		}
		
		boolean writeCheck = TuringMachineServices.outputWriter(results, outputPath, appendWrite);
		if(writeCheck) {
			System.out.println("written file! ");
		}else {
			System.out.println("failed write the file!");
		}
		
		sc.close();
	}
}
