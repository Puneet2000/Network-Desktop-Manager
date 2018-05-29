import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Client {
	Socket client = null;
	public static void main(String args[]) {
		String ip = JOptionPane.showInputDialog("Enter the IP of server");
		String port = JOptionPane.showInputDialog("Enter the port number");
		new Client().initialize(ip,Integer.parseInt(port));
	}
	
	public void initialize(String ip , int port) {
		Robot robot =null;
		Rectangle rec = null;
		System.out.println("Connecting to Server");
		try {
			client = new Socket(ip,port);
			System.out.println("Connection made ..");
			GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev=gEnv.getDefaultScreenDevice();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rec = new Rectangle(dim);
            try {
				robot = new Robot(gDev);
				 drawGUI();
				 new ScreenRecorder(client,robot,rec);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void drawGUI() {
		
	}

}
