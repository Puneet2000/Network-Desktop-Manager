import java.awt.Rectangle;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class CommandSender extends Thread implements KeyListener,MouseListener,MouseMotionListener{
	private Socket cSocket = null;
    private JPanel cPanel = null;
    private PrintWriter writer = null;
    private Rectangle clientScreenDim = null;

    CommandSender(Socket s, JPanel p, Rectangle r) {
    	System.out.println("Command Sender Started");
        cSocket = s;
        cPanel = p;
        clientScreenDim = r;
        cPanel.addKeyListener(this);
        cPanel.addMouseListener(this);
        cPanel.addMouseMotionListener(this);
        try {
            writer = new PrintWriter(cSocket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        double xScale = clientScreenDim.getWidth()/cPanel.getWidth();
        System.out.println("xScale: " + xScale);
        double yScale = clientScreenDim.getHeight()/cPanel.getHeight();
        System.out.println("yScale: " + yScale);
        System.out.println("Mouse Moved");
        writer.println(EnumCommands.MOVE_MOUSE.getAbbrev());
        writer.println((int)(e.getX() * xScale));
        writer.println((int)(e.getY() * yScale));
        writer.flush();
    }

    //this is not implemented
    public void mouseClicked(MouseEvent e) {
        
    }

    public void mousePressed(MouseEvent e) {
    	if (e.getButton() == MouseEvent.BUTTON1) {
        System.out.println("Mouse left Pressed : " + Integer.toString(e.getButton()));
        writer.println(EnumCommands.PRESS_MOUSE.getAbbrev());
        int button = e.getButton();
        writer.println(button);
        writer.flush();
    	}
    	
    	if (e.getButton() == MouseEvent.BUTTON2) {
            System.out.println("Mouse MiddlePressed : " + Integer.toString(e.getButton()));
            writer.println(EnumCommands.PRESS_MOUSE.getAbbrev());
            int button = e.getButton();
            writer.println(button);
            writer.flush();
        	} 
    	
    	if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("Mouse Right Pressed : " + Integer.toString(e.getButton()));
            writer.println(EnumCommands.PRESS_MOUSE.getAbbrev());
            int button = e.getButton();
            writer.println(button);
            writer.flush();
        	}
    }

    public void mouseReleased(MouseEvent e) {
    	if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Mouse left Released : " + Integer.toString(e.getButton()));
            writer.println(EnumCommands.RELEASE_MOUSE.getAbbrev());
            int button = e.getButton();
            writer.println(button);
            writer.flush();
        	}
        	
        	if (e.getButton() == MouseEvent.BUTTON2) {
                System.out.println("Mouse Middle Relesed : " + Integer.toString(e.getButton()));
                writer.println(EnumCommands.RELEASE_MOUSE.getAbbrev());
                int button = e.getButton();
                writer.println(button);
                writer.flush();
            	} 
        	
        	if (e.getButton() == MouseEvent.BUTTON3) {
                System.out.println("Mouse Right Released : " + Integer.toString(e.getButton()));
                writer.println(EnumCommands.RELEASE_MOUSE.getAbbrev());
                int button = e.getButton();
                writer.println(button);
                writer.flush();
            	}
    }

    //not implemented
    public void mouseEntered(MouseEvent e) {
    }

    //not implemented
    public void mouseExited(MouseEvent e) {

    }

    //not implemented
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed");
        writer.println(EnumCommands.PRESS_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
    }

    public void keyReleased(KeyEvent e) {
        System.out.println("Mouse Released");
        writer.println(EnumCommands.RELEASE_KEY.getAbbrev());
        writer.println(e.getKeyCode());
        writer.flush();
    }

}
