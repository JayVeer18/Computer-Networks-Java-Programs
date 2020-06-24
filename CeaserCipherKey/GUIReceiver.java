import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class GUIReceiver extends Frame implements ActionListener
{Panel p1=new Panel();
 Label head,l1,l2,l3;
 TextField t1,t2,t3;
 Button b1,b2;
 Socket s;
 static int key=0;
 public GUIReceiver()
 {setBackground(Color.gray);
  setLayout(new BorderLayout());
  head=new Label("Receiver",Label.CENTER);
  Panel p=new Panel();p.add(head);
  p1.setLayout(new GridLayout(4,2));
  l1= new Label("Message Received:",Label.LEFT);
  t1= new TextField(20);p1.add(l1);p1.add(t1);
  l2= new Label("Key tried:",Label.LEFT);
  t2= new TextField(20);p1.add(l2);p1.add(t2);
  l3= new Label("Decrypted:",Label.LEFT);  
  t3= new TextField(20); p1.add(l3);p1.add(t3);
  b1= new Button("WrongKey");p1.add(b1);
  b2= new Button("CorrectKey");p1.add(b2);
  b1.addActionListener(this);
  b2.addActionListener(this);
  add(p,BorderLayout.NORTH);
  add(p1,BorderLayout.CENTER);
  addWindowListener(new WindowAdapter()
  {public void windowClosing(WindowEvent we)
	  {setVisible(false);dispose();
	   try{s.close();}catch(Exception e){System.out.println(e);}
	  }
  });
  setSize(200,200);
  setVisible(true);
  try{s =new Socket(InetAddress.getLocalHost(),8585);}catch(Exception e){System.out.println(e);}
  receive();
 }
 public void actionPerformed(ActionEvent ae)
 {if(ae.getSource()==b1)
	 {if(b1.getLabel().equals("WrongKey"))decrypt();}
  if(ae.getSource()==b2)
    {System.out.println("success");b1.setLabel("Successfull");b1.setEnabled(false);}
 }
 public void decrypt()
 {String refer="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890.";
  int len=refer.length();
  String s=t1.getText().trim();
  t2.setText(""+key);
   String enc=new String();char ch;
   int index,ci;
  for(int i=0;i<s.length();i++)  
  {ch=s.charAt(i);
   index=refer.indexOf(ch);
   ci=(index-key)%len;
   if(ci<0){ci=ci+len;}
  enc+=refer.charAt(ci);   
  }	
  t3.setText(enc); key++; 
 }
 public void receive()
 {String msg=new String();
  try{int read;byte[] con=new byte[1024];
	  InputStream is =s.getInputStream();
	  /*while((read=is.read(con))!=-1)
	   {msg+=new String(con,0,read);System.out.flush();}*/
      is.read(con);msg=new String(con).trim();
	  System.out.println(msg);
      t1.setText(msg);t2.setText(""+key);	  
	 }catch(Exception e){System.out.println(e);}
 }
 public static void main(String[] args)
 {GUIReceiver g = new GUIReceiver();}
}