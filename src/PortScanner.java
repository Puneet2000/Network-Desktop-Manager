import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;
import java.io.*;
public class PortScanner {

	public static void main (String args[]) {
		new PortScanner().drawGUI();
	}
	public void TcpConnect(String ip) {
		for (int i=0;i<=65535;i++) {
			try {
				Socket s = new Socket(ip,i);
				System.out.println(ip+":"+i );
				s.setSoTimeout(1000);
				InputStream in = s.getInputStream();
				BufferedInputStream bins = new BufferedInputStream(in);
				BufferedReader bin = new BufferedReader(new InputStreamReader(bins));
	            String response = bin.readLine();
	            System.out.println(response);
				s.close();
			} catch (UnknownHostException e) {
				System.out.println("Exc");
				
			} catch (IOException e) {
				
			}
		}
	}
	public void TcpSYN(String ip) {
		
	}
	public void drawGUI() {
		JFrame jf = new JFrame("Port Scanner");
		jf.setSize(200,100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel();
		JButton tcpSyn = new JButton("TCP SYN Scan");
		tcpSyn.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String ip = JOptionPane.showInputDialog("Enter IP address");
				  TcpSYN(ip);
			   
			  } 
			  
			} );
		JButton tcpConnect = new JButton("TCP CONNECT Scan");
		tcpConnect.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String ip = JOptionPane.showInputDialog("Enter IP address");
				  TcpConnect(ip);
			   
			  } 
			  
			} );
		jp.add(tcpSyn);
		jp.add(tcpConnect);
		jf.add(jp);
		jf.setVisible(true);
	}
}
