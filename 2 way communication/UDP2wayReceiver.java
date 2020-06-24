import java.net.*;
import java.util.*;
class UDP2wayReceiver
{
public static void main(String arg[]) throws Exception
{String mssg=" ";
DatagramSocket ds=new DatagramSocket(1234);
byte data[];
do{

data=new byte[100];
DatagramPacket dp=new DatagramPacket(data,0,data.length);
ds.receive(dp);
String msg=new String(data);
System.out.println("Received sender msg:"+msg);

System.out.println("Enter your msg");
Scanner in=new Scanner(System.in);
mssg=in.nextLine();
DatagramPacket dp1=new DatagramPacket(mssg.getBytes(),0,mssg.length(),InetAddress.getByName("APL33"),4567);
ds.send(dp1);


}while(!data.equals("bye"));
ds.close();
}
}