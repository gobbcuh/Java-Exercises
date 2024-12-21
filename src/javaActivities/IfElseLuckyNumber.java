package javaActivities;

import java.util.Scanner;

public class IfElseLuckyNumber 
{
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
		System.out.print("\n\tEnter a 4-digit number: ");
		int luckyNum = s.nextInt();
		
		if (luckyNum < 1000 || luckyNum > 9999)
		{
			System.out.println("\t" + luckyNum + " is not a 4-digit number");
		}
		else 
		{
			// 1234
			int fourthDigit = luckyNum % 10; // 4
			int thirdDigit = (luckyNum / 10) % 10; // 123 % 10 = 3
			int secondDigit = (luckyNum / 100) % 10; // 12 % 10 = 2
			int firstDigit = (luckyNum / 1000) % 10; // 1 % 10 = 1
			
			if (firstDigit + secondDigit == thirdDigit + fourthDigit)
				System.out.println("\t" + luckyNum + " is a LUCKY NUMBER");
			
			else
				System.out.println("\t" + luckyNum + " is not a LUCKY NUMBER");
		}
	}
}
