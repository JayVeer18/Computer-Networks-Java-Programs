import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileTransferServer { 
    ServerSocket ssock;
    Socket socket;
    InetAddress IA ;
    File file;
    public FileTransferServer(int portnumber,String fname)throws Exception
    { ssock = new ServerSocket(portnumber);
      System.out.println("Port "+portnumber+" is opened.");
      //establish connection with client
      connect();
      file = new File(fname);
    }
    
    public void sendFile()throws Exception {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        //Get socket's output stream
        OutputStream os = socket.getOutputStream();
        //Read File Contents into contents array 
        byte[] contents;
        long fileLength = file.length(); 
        long current = 0;         
        //long start = System.nanoTime();
        while(current!=fileLength)
        { 
            int size = 10000;
            if(fileLength - current >= size)
                current += size;    
            else{ 
                size = (int)(fileLength - current); 
                current = fileLength;
            } 
            contents = new byte[size]; 
            bis.read(contents, 0, size); 
            os.write(contents);
            clearConsole();
		   String sen="Sending file ... "+(current*100)/fileLength+"% complete!";
          System.out.print(sen);
        }
        bis.close();
        os.close();
        System.out.println("\nFile sent succesfully!");
        //File transfer done. Close the socket connection!
      socket.close();
      ssock.close();
	  System.out.println("connection  is closed!....");
    }
    private void clearConsole()throws Exception
    {new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}

    private void connect() throws Exception
    { 
     System.out.println("waiting for client connection.....");
     socket = ssock.accept();
     System.out.println("Client " + socket.getInetAddress() + " has connected....");
     //The InetAddress specification
     IA = InetAddress.getByName("localhost");
    }
    
    public static void main(final String[] args) throws Exception {
        try(Scanner sc = new Scanner(System.in))
        {System.out.print("Enter the file with its path:");
         String fname = sc.nextLine();
         System.out.print("Enter the port number on which server to be run:");
         int port = sc.nextInt();
         FileTransferServer fs = new FileTransferServer(port, fname);  
         fs.sendFile(); 
        }catch (Exception e) {
            System.out.println(""+e);
        }
    }
}