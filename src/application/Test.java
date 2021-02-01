package application;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the file path of the input: ");
		String inputPath = sc.nextLine();
		
		System.out.println(inputPath);
		
		String[] path = inputPath.split("input");
		
		for(String str : path) {
			System.out.println(str);
		}
		
		String outputPath = path[0] + "output" + path[1];
		
		System.out.println(outputPath);
		sc.close();
	}
}
