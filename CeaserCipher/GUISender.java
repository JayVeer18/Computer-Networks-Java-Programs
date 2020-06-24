import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class GUISender extends Frame implements ActionListener
{
 Label head,l1,l2,l3;
 TextField t1,t2,t3;
 Button b1,b2;
 ServerSocket ss;
 Socket s;
 public GUISender()
 {setBackground(Color.gray);
  setLayout(new BorderLayout());
  head=new Label("Server",Label.CENTER);
  Panel p=new Panel();p.add(head);
  Panel p1=new Panel();
  p1.setLayout(new GridLayout(4,2));
  l1= new Label("Enter Message:",Label.LEFT);
  t1= new TextField(20);p1.add(l1);p1.add(t1);
  l2= new Label("Key:",Label.LEFT);
  t2= new TextField(20);p1.add(l2);p1.add(t2);
  l3= new Label("Encrypted:",Label.LEFT);  
  t3= new TextField(20); p1.add(l3);p1.add(t3);
  b1= new Button("Encrypt");p1.add(b1);
  b2= new Button("Send");  p1.add(b2);
  b1.addActionListener(this);
  b2.addActionListener(this);
  add(p,BorderLayout.NORTH);
  add(p1,BorderLayout.CENTER);
  addWindowListener(new WindowAdapter()
  {public void windowClosing(WindowEvent we)
	  {setVisible(false);dispose();
	   try{ss.close();}catch(Exception e){System.out.println(e);}
	  }
  });
  setSize(200,200);
  setVisible(true);
  try{ss = new ServerSocket(8585);
 s =ss.accept();}catch(Exception e){System.out.println(e);}
 }
 public void actionPerformed(ActionEvent ae)
 {if(ae.getSource()==b1)
	 {encrypt();}
  if(ae.getSource()==b2)
     {send();}	 
 }
 public void encrypt()
 {String refer="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.";
  int len=refer.length();
  String s=t1.getText().trim();
  int key=Integer.parseInt(t2.getText().trim());
   String enc=new String();char ch;
   int index,ci;
  for(int i=0;i<s.length();i++)  
  {ch=s.charAt(i);
   index=refer.indexOf(ch);
   ci=(index+key)%len;
  enc+=refer.charAt(ci);   
  }	
  t3.setText(enc);  
 }
 public void send()
 {String msg=t3.getText().trim();
  String key=t2.getText().trim();
  try{
	  OutputStream os =s.getOutputStream();
	  System.out.println(key);
	  os.write(key.getBytes());
	  System.out.println(msg);
	  os.write(msg.getBytes());
	  os.flush();}catch(Exception e){System.out.println(e);}
 }
 public static void main(String[] args)
 {GUISender g = new GUISender();}
}