//Interface 
import java.rmi.*;
public interface RmiInter extends Remote
{
 public int sumOfCubes(int n) throws RemoteException;
 public int fact(int n)throws RemoteException;
}