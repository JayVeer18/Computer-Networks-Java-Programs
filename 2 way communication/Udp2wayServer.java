import java.util.*;
import java.net.*;
public class Udp2wayServer
{public static void main(String args[ ]) throws Exception
	{
		DatagramSocket ds= new DatagramSocket(4567);
		String mssg="";
		byte data[];
		System.out.println("Enter the message...and to stop use bye...");
		while(!mssg.equals("bye"))
		{System.out.print("Sender mssg:");
		Scanner sc = new Scanner(System.in);
		mssg=sc.nextLine();	
		DatagramPacket dp = new DatagramPacket(mssg.getBytes(),0,mssg.length( ),InetAddress.getByName("APL34"),1234);
		ds.send(dp);
		data=new byte[100];
		DatagramPacket dp1 = new DatagramPacket(data,0,data.length);
		ds.receive(dp1);
		mssg=new String(data);
		System.out.println("Client mssg:"+mssg);
		}
		ds.close();
	}
	
}