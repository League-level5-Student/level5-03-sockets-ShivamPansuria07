package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) {
	    Client client = new Client("localhost", 12345); // or any other IP and port
	    client.start();
	}

	private String ip;
	private int port;

	Socket connection;
//s
	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
		    try {
		        Object receivedMessage = is.readObject();
		        JOptionPane.showMessageDialog(null, receivedMessage);
		        System.out.println(receivedMessage);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	}
	
	public void sendClick() {
		try {
			if (os != null) {
				os.writeObject("CLICK SENT FROM CLIENT");
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

