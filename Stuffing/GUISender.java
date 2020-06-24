import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.net.*;
public class GUISender extends Frame implements ActionListener
{ServerSocket ss;
  Socket s;
 Label l1,header,l2;
 TextField t1,t2;
 Button b1,b2;
 Panel p1= new Panel();
 public GUISender()
 {setBackground(Color.gray);
  setLayout(new BorderLayout());
  Panel p = new Panel();
  header= new Label("BitStuffing_Sender",Label.CENTER);
  p.add(header);add(p,BorderLayout.NORTH);
  p1.setLayout(new GridLayout(2,3));
  l1= new Label("Enter the message(AlphaNumerics):",Label.LEFT);
  l2= new Label("Stuffed message:",Label.LEFT);
  t1=new TextField(20);
  b1 = new Button("Stuffing");b1.addActionListener(this);
  p1.add(l1);p1.add(t1);p1.add(b1);
  t2=new TextField(20);  
  b2=new Button("send");b2.addActionListener(this);
  p1.add(l2);p1.add(t2);p1.add(b2);
  add(p1,BorderLayout.CENTER);
  
  addWindowListener(new WindowAdapter()
  {public void windowClosing(WindowEvent we)
	  {setVisible(false);dispose();
	   try{ss.close();}catch(Exception e){System.out.println(e);}
	  }	  
  });
  setSize(600,200);
 setVisible(true);
 try{ss = new ServerSocket(8585);
 s =ss.accept();}catch(Exception e){System.out.println(e);}
 }
 public void actionPerformed(ActionEvent ae)
 {if(ae.getSource() == b1)
	 {String msg=bitStuffing();t2.setText(msg.trim());}
  if(ae.getSource() == b2)
	{send();}
 }
 public String bitStuffing()
 {String msg= t1.getText().trim();
  String msgbin=new String();int count=0;String ch,dum;
  String enc=new String();
  for(int i=0;i<msg.length();i++)
  {ch=Integer.toBinaryString(Integer.parseInt(""+(int)msg.charAt(i)));
    int k=ch.length();
   while(k<7){msgbin+="0";k++;}msgbin+=ch;
  }
  for(int i=0;i<msgbin.length();i++)
  {enc+=msgbin.charAt(i);
   if(msgbin.charAt(i)=='1')
   {count++;if(count==5){msgbin+=0;count=0;}}  
   else {count=0;}
  }
  return enc;
 }
 public void send()
 {String msg=t2.getText().trim();
  try{
    OutputStream os = s.getOutputStream();
  os.write(msg.getBytes());
  }catch(Exception e){System.out.println(e);}
 }
 public static void main(String[] args)
 {GUISender g = new GUISender();}
}