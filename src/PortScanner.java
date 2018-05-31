import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class PortScanner {

	public static void main (String args[]) {
		new PortScanner().drawGUI();
	}
	public void scan(String ip) {
		for (int i=0;i<=65535;i++) {
			try {
				Socket s = new Socket(ip,i);
				System.out.println( ip +" is listening to port "+ Integer.toString(i));
				s.close();
			} catch (UnknownHostException e) {
				System.out.println("Exc");
				
			} catch (IOException e) {
				
			}
		}
	}
	public void services(String ip) {
		for (int i=0;i<=65535;i++) {
			try {
				Socket s = new Socket(ip,i);
				s.close();
				 Process p1=Runtime.getRuntime().exec("grep -w "+i +" /etc/services"); 
                 try {
					p1.waitFor();
					 BufferedReader reader=new BufferedReader(new InputStreamReader(p1.getInputStream())); 
	                 String line=reader.readLine(); 
	                 while(line!=null)  
	                 { 
	                    System.out.println(line); 
	                    line=reader.readLine(); 
	                 }
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
                
			} catch (UnknownHostException e) {
				
			} catch (IOException e) {
				
			}
		}
	}
	public void drawGUI() {
		JFrame jf = new JFrame("Port Scanner");
		jf.setSize(200,100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel();
		JButton scan = new JButton("Scan");
		scan.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String ip = JOptionPane.showInputDialog("Enter IP address");
				  scan(ip);
			   
			  } 
			  
			} );
		JButton services = new JButton("Running Services");
		services.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String ip = JOptionPane.showInputDialog("Enter IP address");
				  services(ip);
			   
			  } 
			  
			} );
		jp.add(scan);
		jp.add(services);
		jf.add(jp);
		jf.setVisible(true);
	}
}
