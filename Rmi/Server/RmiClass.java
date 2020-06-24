//Implementing class
import java.rmi.server.*;
import java.rmi.*;
public class RmiClass extends UnicastRemoteObject implements RmiInter
{
 public RmiClass()throws RemoteException
  {}
 public int sumOfCubes(int n) throws RemoteException
  {int res= (n*(n+1))/2;
   return res*res;
  }
 public int factorial(int n)
	{if(n==0 || n==1){return 1;}
	 else{return n*factorial(n-1);}
	} 
 public int fact(int n)throws RemoteException
	{int res=factorial(n);
	 return res;} 
}
