package javaActivities;

import java.util.Scanner;

public class IfElseQuadraticEquation 
{
    public static void main(String[] args) 
    {
        Scanner s = new Scanner(System.in);
        
        System.out.print("A: ");
        double inputA = s.nextDouble();
        System.out.print("B: ");
        double inputB = s.nextDouble();
        System.out.print("C: ");
        double inputC = s.nextDouble();
        
        double answer = (inputB * inputB) - 4.0 * inputA * inputC;
        
        if (answer > 0.0) 
        {
            double root1 = (-inputB + Math.sqrt(answer)) / (2 * inputA);
            double root2 = (-inputB - Math.sqrt(answer)) / (2 * inputA);
            System.out.println("The roots are: " + root1 + " and " + root2);
        } 
        else if (answer == 0.0) 
        {
            double root1 = -inputB / (2 * inputA);
            System.out.println("The root is: " + root1);
        } 
        else 
        {
            System.out.println("No real roots");
        }
    }
}