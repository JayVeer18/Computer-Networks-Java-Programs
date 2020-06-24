import java.net.*;
import java.io.*;
import java.util.*;
public class Sender 
{
 public static void main(String[] args) throws Exception
  {ServerSocket ss= new ServerSocket(2568);
   System.out.println("waiting for receiver...");
   Socket s= ss.accept();
   Socket s1=new Socket(InetAddress.getLocalHost(),2567);
   Scanner sc = new Scanner(System.in);
   OutputStream os = s.getOutputStream();
   System.out.print("Enter the directory to be created including path:");
   String dir=sc.nextLine();
   os.write(dir.getBytes());
   System.out.println("Name Sent...");
   InputStream is = s1.getInputStream();
   byte[] cont= new byte[1024];
   int read;String msg=new String();
   /*while((read = is.read())!=-1)
   {msg+=new String(cont,0,read);}*/
     is.read(cont);
	 msg= new String(cont);
   System.out.println(msg);
   //ss.close();
  }
}