
import java.net.*;
import java.nio.channels.DatagramChannel;

import javax.swing.JTextArea;

import java.io.*;
public class PortScanner {

	public void TcpConnect(String ip,int port,JTextArea jtxt)  {
		int open =1;
		String s="";
		for (int i=1;i<=65535;i++) {
			if (i!=port) {
			String data="";
			Socket socket = new Socket();
			
                try {
                	socket.setReuseAddress(true);
                	socket.setTcpNoDelay(true);
	                socket.connect(new InetSocketAddress(ip, i), 6000);
				} catch (ConnectException e) {
					continue;
				} catch (SocketTimeoutException e) {
					
						open++;
		         	       System.out.println("Port "+Integer.toString(i)+" is filtered");
		         	       continue;
					
				}
                catch (IOException e) {
                	e.printStackTrace();
					continue;
				}
                
                try {
                    InputStreamReader input = new InputStreamReader(socket.getInputStream(), "UTF-8");
                    BufferedReader buffered = new BufferedReader(input);
                    if (i == 22) {
                        data = parseSSH(buffered);
                    } else if (i == 80 || i == 443 || i == 8080) {
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
                        data = parseHTTP(buffered, out,ip);
                    }
                } catch (IOException e) {
                	
                } finally {
                	open++;
         	      jtxt.append( "\n"+("Port "+Integer.toString(i)+" is open"));
         	       System.out.println(data);
                    try {
                        socket.close();
                    } catch (IOException ignored) {
                        // Something's really wrong if we can't close the socket...
                    }
    }
	    }
	    	
		
		}
		jtxt.append( "\n"+("Port "+Integer.toString(port)+" is open"));
		jtxt.append("\n"+("Ports open " + Integer.toString(open)));
		}
	
	public void TcpSYN(String ip,int port,JTextArea jtxt) {
		
	}
	private String parseSSH(BufferedReader reader) throws IOException {
        try {
            return reader.readLine();
        } finally {
            reader.close();
        }
}
	private String parseHTTP(BufferedReader reader, PrintWriter writer,String ip) throws IOException {
        writer.println("GET / HTTP/1.1\r\nHost: " + ip + "\r\n");
        char[] buffer = new char[256];
        reader.read(buffer, 0, buffer.length);
        writer.close();
        reader.close();
        String data = new String(buffer).toLowerCase();

        if (data.contains("apache") || data.contains("httpd")) {
            return "Apache";
        }

        if (data.contains("iis") || data.contains("microsoft")) {
            return "IIS";
        }

        if (data.contains("nginx")) {
            return "NGINX";
        }

        return null;
}
	public void UdpScan(String ip,int port,JTextArea jtxt) {
		int open =1;
		String s="";
		for (int i=1;i<=10;i++) {
			if(i!=port) {
			String response="";
	        try{
	        	String m ="Hello";
	            byte [] bytes = m.getBytes();
	            DatagramSocket ds = new DatagramSocket();
	            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(ip),i);
	            DatagramChannel dChannel = DatagramChannel.open();
	            dChannel.connect(new InetSocketAddress(ip, i));
	            dChannel.configureBlocking(true);
	            ds = dChannel.socket();
	            ds.setSoTimeout(1000);
	            ds.send(dp);
	            dp = new DatagramPacket(bytes, bytes.length);
	            Thread.sleep(1000);
	            ds.receive(dp);
	            System.out.println(ds);
	            response = dp.toString();
	            ds.disconnect();
	            dChannel.disconnect();
	            dChannel.close();
	            ds.close();
	        }
	        catch(PortUnreachableException e){
	        	 continue;
	        }
	        catch(InterruptedIOException e){
	        	 continue;
	        }
	        catch(IOException e){
	        	continue;
	        }
	        catch(Exception e){
	        	continue;
	        }
	       open++;
	       jtxt.append( "\n"+("Port "+Integer.toString(i)+" is open"));
	       System.out.println(response);
		}
		
	}
		jtxt.append( "\n"+("Port "+Integer.toString(port)+" is open"));
		jtxt.append("\n"+("Ports open " + Integer.toString(open)));
	
}}
