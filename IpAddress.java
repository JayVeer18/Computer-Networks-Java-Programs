import java.io.*;
import java.net.*;
import java.util.*;
class IpAddress
{InetAddress ip;
  public IpAddress()throws Exception
  { System.out.println("-------------------Details of LocalHost-------------------");
    ip= InetAddress.getLocalHost();
    if(ip.isReachable(1000))
     {System.out.println("IP address:"+ip.getHostAddress()+"\nHostName:"+ip.getHostName());}
    else
    {System.out.println("not found");}
    System.out.println("---------------------------------------------------------");
  }
  public void throughCmd(String ip) throws Exception
  { System.out.println("-------------------Executing through commands-------------------");
    String pingcmd="ping "+ip;
    Runtime r= Runtime.getRuntime();
    Process p = r.exec(pingcmd);
    BufferedReader dat = new BufferedReader(new InputStreamReader(p.getInputStream()));
    //DataInputStream is= new DataInputStream(p.getInputStream());
    //String data=is.readLine();
    String data = dat.readLine();
    while(data !=null)
     {data=dat.readLine();
     System.out.println(data);}
     System.out.println("---------------------------------------------------------------"); 
  }
  public void getDetails(String name)throws Exception
  { System.out.println("-------------using InetAddress class methods-------------");
    ip = InetAddress.getByName(name);
    System.out.println("host ip          :"+ip.getHostAddress());
    System.out.println("hostname         :"+ip.getHostName());
    System.out.println("Local System     :"+InetAddress.getLocalHost());
    System.out.println("whether host available:"+ip.isReachable(1000));
    System.out.println("---------------------------------------------------------");
  }
  public static void main (String args[])
  {
    try(Scanner sc=new Scanner(System.in);)
    { IpAddress address = new IpAddress();
      System.out.print("enter name/IPAddress of the host:");
      String str=sc.nextLine();
      address.throughCmd(str);
      address.getDetails(str);
    }catch(Exception e)
       {System.out.println(""+e);}
  }

}