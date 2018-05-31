import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
public class Server {
	private JFrame jFrame=null;
	private JDesktopPane desktop=null;
	ServerSocket sc=null;
	 public static void main(String args[]){
	        String port = JOptionPane.showInputDialog("Please enter listening port");
	        new Server().initialize(Integer.parseInt(port));
	    }

	    public void initialize(int port){

	        try {
	            sc = new ServerSocket(port);
	            drawGUI();
	            while(true){
	                Socket client = sc.accept();
	                System.out.println("New client Connected to the server");
	                new ClientHandler(client,desktop);
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    public void drawGUI() {
	    	jFrame = new JFrame("Server Window");
	    	desktop = new JDesktopPane();
	    	jFrame.add(desktop,new BorderLayout().CENTER);
	    	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	jFrame.setExtendedState(jFrame.getExtendedState()| JFrame.MAXIMIZED_BOTH);
	    	jFrame.setVisible(true);
	    	
	    }

}
