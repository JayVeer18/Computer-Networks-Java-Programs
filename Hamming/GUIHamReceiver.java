import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class GUIHamReceiver extends Frame implements ActionListener
{Label head,l1,l2,l3;
 TextField t1,t2,t3;
 Button b1;
 Socket s;
 int parity;
 public GUIHamReceiver()
 {setLayout(new BorderLayout());
  head =new Label("Receiver",Label.CENTER);
  Panel p1= new Panel();p1.add(head);
  add(p1,BorderLayout.NORTH);
  Panel p= new Panel();
  p.setLayout(new GridLayout(4,2));
  l1= new Label("Received Code:",Label.LEFT);p.add(l1);
  t1= new TextField(50);p.add(t1);
  l2= new Label("Received Text:",Label.LEFT);p.add(l2);
  t2= new TextField(50);p.add(t2); 
  l3= new Label("Error Position:",Label.LEFT);p.add(l3);
  t3= new TextField(50);p.add(t3);   
  b1= new Button("DetectError");p.add(b1);
  b1.addActionListener(this);
  add(p,BorderLayout.CENTER);
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
	 {String msg=Hamming();t2.setText(msg.trim());}
 }
 public String Hamming()
 {String input,output="";
  input=t1.getText().trim();
  int len=input.length();
  int k=0,j=0,value=0;
  int[]syndrome=new int[parity];
  int[]par=new int[parity];
  int[] code=new int[len+1];
  for(int i=1;i<len+1;i++)
  {code[i]=Integer.parseInt(""+input.charAt(i-1));
   //if(i==5){code[i]^=1;}
  }
  for(int i=0;i<parity;i++)
  {k=(int)Math.pow(2,i);par[i]=code[k];code[k]=0;}  
 //calculating [parity
 for(int i=0;i<parity;i++)
 {int index=(int)Math.pow(2,i);
	 for(j=1;j<code.length;j++)
	 {if( ((j>>i)&1) ==1)
		 {code[index]^=code[j];}
	 }
 }
  for(int i=0;i<parity;i++)
  {k=(int)Math.pow(2,i);
   syndrome[i]=par[i]^code[k];
   value+=(value*2)+syndrome[i]; 
  }
 if(value==0){t3.setText("No error");}
 else{code[value]^=1;} 
  int q=0;
  for(j=1;j<code.length;j++)
  {if((int)Math.pow(2,q)==j){q++;}
   else{output+=code[j];}
  }
  input="";
  for(int i=0;i<output.length();i=i+7)
  {System.out.println(""+i);
   String s= output.substring(i,i+7);
  input+=(char)Integer.parseInt(s,2);}
 return input;
 }
 public void receive()
 {try{byte[] contents= new byte[1024];
  InputStream is = s.getInputStream();
  int read;String data=new String();
  is.read(contents);data=new String(contents).trim();
  parity=Integer.parseInt(data);
  is.read(contents);data=new String(contents).trim();
  System.out.println("received..");
  t1.setText(data);
 }catch(Exception e){System.out.println(e);}
 }
 public static void main(String[] args)
 {GUIHamReceiver g = new GUIHamReceiver();}
}