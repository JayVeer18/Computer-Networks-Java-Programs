//Remote Server
import java.rmi.*;
import java .rmi.registry.*;
public class RmiServer
{
 public static void main(String args[])throws Exception 
 {String url="rmi://localhost:1233/RmiServer";
  RmiInter obj= new RmiClass();
  LocateRegistry.createRegistry(1233);
  Naming.rebind(url,obj);
  System.out.println("Remote method is invoked....");
 }  
}
