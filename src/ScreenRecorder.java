import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.io.*;
public class ScreenRecorder extends Thread {
  Rectangle rec = null;
  Robot robot = null;
  Socket client = null;
  boolean Continue = true;
  public ScreenRecorder(Socket client,Robot robot,Rectangle rec) {
	  this.client = client;
	  this.robot = robot;
	  this.rec = rec;
	  start();
  }
  public void run() {
	  ObjectOutputStream oos=null;
	  try {
		oos = new ObjectOutputStream(client.getOutputStream());
		System.out.println("Sending Screem Dimension");
		oos.writeObject(rec);
		System.out.println("Screen Dimension Sent");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  while(Continue) {
		  BufferedImage image = robot.createScreenCapture(rec);
      
          ImageIcon imageIcon = new ImageIcon(image);

          try {
              System.out.println("Trying to send image");
              oos.writeObject(imageIcon);
              oos.reset(); 
              System.out.println("New screenshot sent");
          } catch (IOException ex) {
        	  Continue = false;
             ex.printStackTrace();
          }

          try{
              Thread.sleep(60);
          }catch(InterruptedException e){
        	  Continue = false;
              e.printStackTrace();
          }
	  }
  }
}
