package javaActivities;

import java.util.Scanner;

public class WhileDoWhile2 
{

	public static void main(String[] args) 
	{
		// QUIZ GAME WITH LIVES CHALLENGE
		
		Scanner s = new Scanner(System.in);
		
		String answer;
		int lives = 3;
		
		do
		{
			System.out.println("Who is the National Hero of the Philippines?");
			System.out.print("Your answer: ");
			answer = s.nextLine();
			System.out.println();
			
			if (answer.equalsIgnoreCase("Jose Rizal"))
			{
				System.out.println("CONGRATULATIONS!");
				break;
			}
			
			lives--;
			if (lives == 0)
				System.out.println("YOU HAVE LOST!");
		
		} while (answer != "Jose Rizal" && lives > 0);
		
	}

}
