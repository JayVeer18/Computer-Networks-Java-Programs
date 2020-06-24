//Remote Client
import java.io.*;
import java.util.*;
import java.rmi.*;
import java .rmi.registry.*;
public class RmiClient
{
 public static void main(String args[])throws Exception 
 {String url="rmi://localhost:1233/RmiServer";
  RmiInter obj=(RmiInter)Naming.lookup(url); 
   System.out.println("Enter the n value:");
   Scanner sc = new Scanner(System.in);
   int n= sc.nextInt();
   System.out.println("Sum of cubes of "+n+" natural numbers:"+obj.sumOfCubes(n));
   System.out.println("Factorial of "+n+":"+obj.fact(n));
  }  
}
