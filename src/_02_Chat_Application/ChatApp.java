package _02_Chat_Application;

import java.awt.TextArea;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

// ask the user if they want to be server or not or two classes(server and client classes)
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

public class ChatApp extends JFrame {
//	JButton button = new JButton("CLICK");
	Server server;
	Client client;
	String input;
	static JTextField t;
	JLabel label = new JLabel();
	public static void main(String[] args) {
		new ChatApp();
	}
	// server, client, chat app class
	public ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
	
			// add(button);
			setVisible(true);
			setSize(400, 300);
			t = new JTextField(16);
			add(t);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			t.addActionListener((e)->{
		
				input=t.getText();
				label.setText(input);
			});
			add(label);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
}

