import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
public class CommandReceiver extends Thread {
	Socket socket = null;
    Robot robot = null;
    boolean continueLoop = true;

    public CommandReceiver(Socket socket, Robot robot) {
        this.socket = socket;
        this.robot = robot;
        start();
    }

    public void run(){
        Scanner scanner = null;
        try {
            scanner = new Scanner(socket.getInputStream());

            while(continueLoop){
                System.out.println("Waiting for command");
                int command = scanner.nextInt();
                System.out.println("New command: " + command);
                switch(command){
                    case -1:
                        { 
                        	int button = scanner.nextInt();
                            switch(button) {
                            case 1:
                            	robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        	break;
                            case 2:
                            	robot.mousePress(InputEvent.BUTTON2_DOWN_MASK);
                            break;
                            case 3:
                            	robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                            break;
                            }
                        
                        }
                    break;
                    case -2:
                        { 
                        	int button = scanner.nextInt();
                            switch(button) {
                            case 1:
                            	robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        	break;
                            case 2:
                            	robot.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
                            break;
                            case 3:
                            	robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                            break;
                            }
                        
                        }
                    break;
                    case -3:
                        robot.keyPress(scanner.nextInt());
                    break;
                    case -4:
                        robot.keyRelease(scanner.nextInt());
                    break;
                    case -5:
                        robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
