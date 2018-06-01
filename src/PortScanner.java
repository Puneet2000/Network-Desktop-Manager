import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.*;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;

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
				
			}catch (SocketTimeoutException e) {
				System.out.println("Timeout");
				
			}catch (IOException e) {
				
			}
		}
	}
	public void TcpSYN(String ip) {
		
	}
	public void UdpScan(String ip) {
		int open =0;
		for (int i=1;i<=10;i++) {
			DatagramSocket ds;
	        DatagramPacket dp;
	        DatagramChannel dChannel;
	        try{
	            byte [] bytes = new byte[128];
	            ds = new DatagramSocket();
	            dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(ip),i);
	            dChannel = DatagramChannel.open();
	            dChannel.connect(new InetSocketAddress(ip, i));
	            dChannel.configureBlocking(true);
	            ds = dChannel.socket();
	            ds.setSoTimeout(1000);
	            ds.send(dp);
	            dp = new DatagramPacket(bytes, bytes.length);
	            Thread.sleep(1000);
	            ds.receive(dp);

	            if (!dChannel.isConnected() || !dChannel.isOpen()){
	                ds.close();
	            }
	            System.out.println(Integer.toString(i) +" is open");
	            String response = dp.toString();
	            System.out.println(response);
	            ds.disconnect();
	            dChannel.disconnect();
	            dChannel.close();
	            ds.close();
	        }
	        catch(PortUnreachableException e){
	        	 
	        }
	        catch(InterruptedIOException e){
	        	 
	        }
	        catch(IOException e){
	        	
	        }
	        catch(Exception e){
	        	
	        }
	       open++;
		}
		System.out.println("Open ports : "+Integer.toString(open));
	}
	public void drawGUI() {
		JFrame jf = new JFrame("Port Scanner");
		jf.setSize(200,150);
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
		JButton udpScan = new JButton("UDP Scan");
		udpScan.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String ip = JOptionPane.showInputDialog("Enter IP address");
				  UdpScan(ip);
			   
			  } 
			  
			} );
		jp.add(tcpSyn);
		jp.add(tcpConnect);
		jp.add(udpScan);
		jf.add(jp);
		jf.setVisible(true);
	}
}
