import java.util.*;
import java.io.*;
import java.net.*;
public class Sender
{ServerSocket ss;
 Socket s,s1;
 String s2="";
 int ws=4,wc=0,wb=0;
 int arr[]=new int[20];
 InputStream is;
 OutputStream os ;
 public Sender()throws Exception
 {ss= new ServerSocket(1234);
  System.out.println("waiting for Receiver");
  s=ss.accept();
  s1=new Socket(InetAddress.getLocalHost(),2345);
  is =s1.getInputStream();
  os=s.getOutputStream();
	 for(int i=0;i<20;i++){arr[i]=i;} 
 }
 public void send()throws Exception
 {while(arr[wc]<=18){
  while(wc-wb<ws)
  {s2=""+arr[wc++];
   System.out.println("packet sent:"+s2);
   os.write(s2.getBytes());}
 }}
public void rcvack()throws Exception
{String s3="";int k=0;
 while(k<=18){
 try{s1.setSoTimeout(1000);
  byte[] con=new byte[1024];
  is.read(con);
  s3= new String(con).trim();
  k=Integer.parseInt(s3);
  if(k>0)
  {System.out.println("Received ACK:"+s3);wb=k;}
  if(k<0)
  {k=(-k);
   System.out.println("Received NACK:"+k);wb=k;wc=k;
   while(wc-wb<ws)
  {s2=""+arr[wc++];wc%=ws;
   System.out.println("packet retransmited sent:"+s2);
   os.write(s2.getBytes());}
   }
	}catch(SocketTimeoutException  se)
		{System.out.println("TimeOut");
			while(wc-wb<ws)
			{s2=""+arr[wc++];wc%=ws;
			 System.out.println("packet retransmited sent:"+s2);
			 os.write(s2.getBytes());}
		}
  }ss.close();
  s1.close();
 } 
 public static void main(String[] args)throws Exception
 {Sender se= new Sender();
  Runnable a= new Runnable()
  {public void run()
  {try{se.send();}catch(Exception e){System.out.println(e);}} };
  Runnable b = new Runnable()
   {public void run()
   {try{se.rcvack();}catch(Exception e){System.out.println(e);}} }; 
 Thread t1= new Thread(a);t1.start();
 Thread t2= new Thread(b);t2.start(); 
 try{t1.join();t2.join();}catch(Exception e){System.out.println(e);}
 }
}