import java.net.*;
import java.io.*;
import java.util.*;
public class Receiver 
{
 public static void main(String[] args) throws Exception
  {Socket s= new Socket(InetAddress.getLocalHost(),2568);
  ServerSocket ss= new ServerSocket(2567);
  Socket s1= ss.accept();
   System.out.println("Connection established...");
   InputStream is = s.getInputStream();
   byte[] cont= new byte[1024];
   int read;String msg=new String();
   System.out.println("hai");
   /*while((read = is.read(cont))!=-1)
   {msg+=new String(cont,0,read);System.out.flush();System.out.print((char)read);}*/
   read=is.read(cont);msg= new String(cont,0,read);
   System.out.println("Directory name:"+msg);
   File f= new File(msg);
   String dir=new String();
    if(f.mkdir()){dir="Directory created Successfully...";}
	else{dir="Directory creation failed!";}
   OutputStream os = s1.getOutputStream();
   os.write(dir.getBytes());
   System.out.println(dir);
   //s.close();
  }
}