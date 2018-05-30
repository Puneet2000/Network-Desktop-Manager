import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ScreenReceiver extends Thread{
	
	private ObjectInputStream cObjectInputStream = null;
    private JPanel cPanel = null;
    private boolean continueLoop = true;

    public ScreenReceiver(ObjectInputStream ois, JPanel p) {
        cObjectInputStream = ois;
        cPanel = p;
        start();
    }

    public void run(){
        
            try {
                while(continueLoop){
                    ImageIcon imageIcon = (ImageIcon) cObjectInputStream.readObject();;
                    Image image = imageIcon.getImage();
                    image = image.getScaledInstance(cPanel.getWidth(),cPanel.getHeight()
                                                        ,Image.SCALE_FAST);
                    Graphics graphics = cPanel.getGraphics();
                    graphics.drawImage(image, 0, 0, cPanel.getWidth(),cPanel.getHeight(),cPanel);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
          } catch(ClassNotFoundException ex){
              ex.printStackTrace();
          }
     }
}
