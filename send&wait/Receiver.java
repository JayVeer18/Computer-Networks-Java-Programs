import java.util.*;
import java.io.*;
import java.net.*;
public class Receiver
{
 public static void main(String[] args)throws Exception
 {Socket s1=new Socket(InetAddress.getLocalHost(),1234);
  ServerSocket ss= new ServerSocket(2345);
  Socket s=ss.accept();
  System.out.println("waiting for Sender");  
  InputStream is =s1.getInputStream();
  OutputStream os =s.getOutputStream();
  String s2="",s3="";
  //Random r=new Random(5);
  int k=2,i=0;
 do
 {i++;
  byte[] con=new byte[1024];
  is.read(con);
  s2= new String(con).trim();
  System.out.println("Sender:"+s2);
  s3="ACK";
  if(i==k+1)
  {Thread.sleep(1000);continue;}
  if(i==k){s3="NACK";}
  if(s2.equals("bye")){s3="FIN";}
  System.out.println("Sending:"+s3);
  os.write(s3.getBytes()); 
 }while(!s2.equals("bye"));
ss.close();
s1.close(); 
 }
 
}