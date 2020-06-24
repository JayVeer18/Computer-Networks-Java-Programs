import java.util.*;
import java.io.*;
import java.net.*;
public class Sender
{
 public static void main(String[] args)throws Exception
 {ServerSocket ss= new ServerSocket(1234);
  System.out.println("waiting for Receiver");
  Socket s=ss.accept();
  Socket s1=new Socket(InetAddress.getLocalHost(),2345);
  InputStream is =s1.getInputStream();
  OutputStream os =s.getOutputStream();
  String s2=new String();
  Scanner sc= new Scanner(System.in);
  String s3="";
 
 do
 {try{s1.setSoTimeout(1000);
  System.out.print("enter message:");
  s2= sc.nextLine();
  os.write(s2.getBytes());
  byte[] con=new byte[1024];
  is.read(con);
  s3= new String(con).trim();
  System.out.println("Receiver:"+s3);
  while(s3.equals("NACK"))
  {System.out.println("Retransmitted:"+s2);
   os.write(s2.getBytes());
  byte[] con1=new byte[1024];
  is.read(con1);
  s3= new String(con1).trim();
  System.out.println("Receiver:"+s3);
  }  
 }
 catch(SocketTimeoutException  se)
  {System.out.println("TimeOut");
   System.out.println("Retransmitted:"+s2);
   os.write(s2.getBytes());
  byte[] con1=new byte[1024];
  is.read(con1);
  s3= new String(con1).trim();
  System.out.println("Receiver:"+s3);
  }
 }while(!s3.equals("FIN"));  
 ss.close();
 s1.close();
 }
}