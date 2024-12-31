package javaActivities;

import java.util.Scanner;

public class WhileDoWhile3
{
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
        
        String answer; 
        int lives = 3;
        
        while (lives > 0)
        {
            System.out.print("Who's my crush?\nYour Answer: ");
            answer = s.nextLine();
            
            if (answer.equalsIgnoreCase("Trishia Anne")) 
            {
                System.out.println("CONGRATULATIONS!");
                break;
            }
            else 
            {
                lives--;
                if (lives == 0) 
                {
                    System.out.println("FAILED");
                }
            }
        }
	}
}
