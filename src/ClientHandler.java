import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.beans.PropertyVetoException;
import java.io.*;
import java.net.*;
public class ClientHandler extends Thread {
	Socket client =null;
	JDesktopPane desktop =null;
	JInternalFrame iframe = new JInternalFrame("Client screen",true,true);
	JPanel panel = new JPanel();
	boolean c =true;
	public ClientHandler(Socket client,JDesktopPane desktop) {
		this.client = client;
		this.desktop = desktop;
		start();
	}
	
	public void run() {
        Rectangle clientScreenDim = null;
        ObjectInputStream ois = null;
        drawGUI();
   

        try{
            ois = new ObjectInputStream(client.getInputStream());
            clientScreenDim =(Rectangle) ois.readObject();
            System.out.println("dim Received");
        }catch(IOException ex){
        	System.out.println("dim not Received");
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
        	System.out.println("dim not Received");
            ex.printStackTrace();
        }
        
        new ScreenReceiver(ois,panel);
        new CommandSender(client,panel,clientScreenDim);
	}
	
	public void drawGUI() {
		iframe.setLayout(new BorderLayout());
        iframe.getContentPane().add(panel,BorderLayout.CENTER);
        iframe.setSize(100,100);
        desktop.add(iframe);
        try {
            iframe.setMaximum(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
        panel.setFocusable(true);
        iframe.setVisible(true);
	}

}
