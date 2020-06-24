import java.util.*;
import java.net.*;
import java.io.*;
public class UdpReceiver
{
 public static void main(String[] args)throws Exception
  {DatagramSocket ds= new DatagramSocket(1234);
   DatagramPacket dp,dp1;
   InetAddress ip = InetAddress.getLocalHost();
   byte[] contents= new byte[1024];
   dp=new DatagramPacket(contents,0,contents.length);
   ds.receive(dp);
   String msg= new String(contents).trim();
   msg=msg.substring(5);
   System.out.println("directory path:"+msg);
   File f=new File(msg);
    String data="files in directory("+msg+"):";
    if(f.exists())
	{String[] files=f.list();
	for(int i=0;i<files.length;i++)data+="\n"+files[i];}
    else{data="directory not found!";}
   DatagramSocket ds1= new DatagramSocket();   
   dp1= new DatagramPacket(data.getBytes(),0,data.length(),ip,2345);
   ds1.send(dp1);
   System.out.println(data);
   ds.close();ds1.close();
  }
}