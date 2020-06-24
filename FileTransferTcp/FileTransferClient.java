import java.io.*;
import java.net.*;
import java.util.Scanner;
public class FileTransferClient { 
    Socket socket;
    FileOutputStream fos;
    public FileTransferClient(String host,int port,String fname)throws Exception
    {//Initialize socket
     socket = new Socket(InetAddress.getByName(host),port);
     System.out.println("Successfully connected to the server!.....");
     fos = new FileOutputStream(fname);
    }
    
    public void writeFile() throws Exception{
        byte[] contents = new byte[10000];
        //To write data into file
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        // to get the input from server
        InputStream is = socket.getInputStream();
                //No of bytes read in one read() call
        int bytesRead = 0; 
        while((bytesRead=is.read(contents))>0)
            {bos.write(contents, 0, bytesRead);} 
        bos.close();
        is.close();    
        System.out.println("File saved successfully!");
        socket.close();
        System.out.println("Connection closed.");
    }


    public static void main(String[] args) throws Exception {
        try(Scanner sc = new Scanner(System.in))
        {System.out.print("Enter host name(for localhost:enter localhost): ");
         String host = sc.nextLine();
         System.out.print("Enter the server port number:");
         int port = sc.nextInt();
         String dummy = sc.nextLine();//removes \n after the number you have entered
         System.out.print("Enter the name to which file to be written:");  
         String name= sc.nextLine();
         FileTransferClient ftc = new FileTransferClient(host,port,name);
         ftc.writeFile();
        }catch(Exception e)
            {System.out.println(""+e);}      
    }
}
