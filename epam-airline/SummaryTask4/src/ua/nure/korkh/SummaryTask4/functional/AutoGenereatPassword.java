package ua.nure.korkh.SummaryTask4.functional;

import java.util.Random;

public class AutoGenereatPassword {
	
	public static String genereat(int lenght){
		Random ran = new Random();
		StringBuilder pass = new StringBuilder();
		int next = 0;
		int range = 0;
		for (int j = 0; j < lenght; j++){
			switch(ran.nextInt(3)){
			// 0 - 9
			case 0: 
				next = 48;
				range = 10;
				break;
			//A-Z
			case 1:
				next = 65;
				range = 26;
				break;
			//a-z
			case 2:
				next = 97;
				range = 26;
				break;
			}
			pass.append((char)((ran.nextInt(range)) + next));
		}
		return pass.toString();
	}

}
