import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;
public class GUIReceiver extends Frame implements ActionListener
{Socket s;
 Label l1,header,l2;
 TextField t1,t2;
 Button b1;
 Panel p1= new Panel();
 public GUIReceiver()
 {setBackground(Color.gray);
  setLayout(new BorderLayout());
  Panel p = new Panel();
  header= new Label("BitStuffing_Receiver",Label.CENTER);
  p.add(header);add(p,BorderLayout.NORTH);
  p1.setLayout(new GridLayout(2,3));
  l1= new Label("Stuffed message:",Label.LEFT);
  l2= new Label("UnStuffed message:",Label.LEFT);
  t1=new TextField(20);
  b1 = new Button("UnStuffing");b1.addActionListener(this);
  p1.add(l1);p1.add(t1);p1.add(b1);
  t2=new TextField(20);  
  p1.add(l2);p1.add(t2);
  add(p1,BorderLayout.CENTER);
  
  addWindowListener(new WindowAdapter()
  {public void windowClosing(WindowEvent we)
	  {setVisible(false);dispose();
	   try{s.close();}catch(Exception e){System.out.println(e);}
	  }	  
  });
  setSize(600,200);
 setVisible(true);
 try{s =new Socket(InetAddress.getLocalHost(),8585);}catch(Exception e){System.out.println(e);}
  System.out.println("connected..");
  receive();
 }
 public void actionPerformed(ActionEvent ae)
 {if(ae.getSource() == b1)
	 {String msg=bitUnStuffing();t2.setText(msg.trim());}
  
 }
 public String bitUnStuffing()
 {String msg= t1.getText().trim();
  String msgbin=new String();int count=0;String ch,dum;
  String enc=new String();
  for(int i=0;i<msg.length();i++)
   {enc+=msg.charAt(i);
	 if(msg.charAt(i)=='1')
	   {count++;if(count==5){i++;count=0;}}
     else{count=0;}
	   
   }
  for(int i=0;i<enc.length();i=i+7)
  {System.out.println(""+i);
   String s= enc.substring(i,i+7);System.out.println(""+s);
   msgbin+=(char)Integer.parseInt(s,2);
  }
  return msgbin;
 }
 public void receive()
 {try{byte[] contents= new byte[1024];
  InputStream is = s.getInputStream();
  int read;String data=new String();
  /*while((read=is.read(contents))!=-1)
  {data+=new String(contents,0,read);System.out.flush();}*/
 is.read(contents);data=new String(contents).trim();
 System.out.println("received..");
 t1.setText(data);
 }catch(Exception e){System.out.println(e);}
 }
 public static void main(String[] args)
 {GUIReceiver g = new GUIReceiver();}
}