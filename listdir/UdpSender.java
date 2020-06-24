import java.util.*;
import java.net.*;
import java.io.*;
public class UdpSender
{
 public static void main(String[] args)throws Exception
  {DatagramSocket ds= new DatagramSocket();
   DatagramPacket dp,dp1;
   InetAddress ip = InetAddress.getLocalHost();
   Scanner sc=new Scanner(System.in);
   System.out.print("enter the directory name to be listed with contents:");
   String msg="list ";
   msg+=sc.nextLine();
   dp=new DatagramPacket(msg.getBytes(),0,msg.length(),ip,1234);
   ds.send(dp);
   DatagramSocket ds1= new DatagramSocket(2345);
   byte[] contents= new byte[1024];
   dp1= new DatagramPacket(contents,0,contents.length);
   ds1.receive(dp1);
   msg=new String(contents).trim();
   System.out.println(msg);
   ds.close();ds1.close();
  }
}