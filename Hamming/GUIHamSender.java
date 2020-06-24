import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class GUIHamSender extends Frame implements ActionListener
{Label head,l1,l2;
 TextField t1,t2;
 Button b1,b2;
 ServerSocket ss;
 Socket s;
 int parity=1;
 public GUIHamSender()
 {setLayout(new BorderLayout());
  head =new Label("Sender",Label.CENTER);
  Panel p1= new Panel();p1.add(head);
  add(p1,BorderLayout.NORTH);
  Panel p= new Panel();
  p.setLayout(new GridLayout(3,2));
  l1= new Label("Enter Text:",Label.LEFT);p.add(l1);
  t1= new TextField(50);p.add(t1);
  l2= new Label("HammingCode:",Label.LEFT);p.add(l2);
  t2= new TextField(50);p.add(t2);  
  b1= new Button("GenerateCode");p.add(b1);
  b2= new Button("send");p.add(b2);
  b1.addActionListener(this);
  b2.addActionListener(this);	
  add(p,BorderLayout.CENTER);
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
	 {String msg=Hamming();t2.setText(msg.trim());}
  if(ae.getSource() == b2)
	{send();}
 }
 public String Hamming()
 {String input,s,output="",binstring="";
  input=t1.getText().trim();
  for(int i=0;i<input.length();i++)
  {s=Integer.toBinaryString((int)input.charAt(i));
   int k=s.length();
   while(k<7){binstring+="0";k++;}
   binstring+=s;
  }  
  int len=binstring.length();
  int k=0,j=0;
  while(Math.pow(2,parity) < (len+parity+ 1)){parity++;}
  int[] code=new int[len+parity+1];
  for(int i=1;i<=len+parity;i++)
  {if((int)Math.pow(2,k)==i)
	  {code[i]=0;k++;}
   else{code[i]=Integer.parseInt(""+binstring.charAt(j));j++;}	  
  }
 for(int i=0;i<parity;i++)
 {int index=(int)Math.pow(2,i);
	 for(j=1;j<code.length;j++)
	 {if( ((j>>i)&1) ==1)
		 {code[index]^=code[j];}
	 }
 }
 for(j=1;j<code.length;j++){output+=code[j];}
 System.out.println("HammingCode:"+output); 
 return output;
 }
 public void send()
 {String msg=t2.getText().trim();
  try{
    OutputStream os = s.getOutputStream();
	String p=""+parity;
	os.write(p.getBytes());
  os.write(msg.getBytes());
  }catch(Exception e){System.out.println(e);}
 }
 public static void main(String[] args)
 {GUIHamSender g = new GUIHamSender();}
}