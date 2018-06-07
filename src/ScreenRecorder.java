import java.net.*;
import java.util.HashMap;

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
		oos.writeObject(rec);
		oos.reset();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		  HashMap<String,String> props = new GetSystemProperties().getProperties();
		oos.writeObject(props);
		oos.reset();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  while(Continue) {
		  BufferedImage image = robot.createScreenCapture(rec);
      
          ImageIcon imageIcon = new ImageIcon(image);

          try {
              oos.writeObject(imageIcon);
              oos.reset(); 
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
