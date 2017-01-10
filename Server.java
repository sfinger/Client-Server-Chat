/*
 * Programmer: Sylvia Finger
 * Notes:   Sometimes, on the initial run of the Server, it throws connection errors.
 * 			Sometimes it does it more than once.
 * 			But if you keep running it, it will eventually work.
 * 
 */
	 import java.io.DataInputStream;
	 import java.io.DataOutputStream;
	 import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Scanner;

	 public class Server extends Thread{
	 	
	 	private ServerSocket serverSocket;

	 	public Server(int port) throws IOException{
	 		serverSocket = new ServerSocket(port);
	 	}
	 	
	 	public void run(){
	 		Scanner oScan = new Scanner(System.in);
 				try{
	 				Socket clientSocket = serverSocket.accept(); 
	 				
	 				String prompt = "";
	 				while(prompt != ".." || prompt != ".."){
	 				String in = new DataInputStream(clientSocket.getInputStream()).readUTF();
	 				System.out.println(in+ "\t\t\tLength: " + (in.length()-8));
	 				
	 				//prompts the server to reply
	 				System.out.println("Message the client below:");
	 				prompt = oScan.nextLine();
	 				new DataOutputStream(clientSocket.getOutputStream()).writeUTF("Server: " + prompt); 
	 				}
	 				
	 			clientSocket.close();
	 		}
	 		
	 		catch(IOException e) {
	 			e.printStackTrace();
	 		}
	 	}

//starts the server thread
	public static void main(String[] args) {
		
		System.out.println("Once the connection has been made, please enter '..' to stop.");
		try{
			Thread c = new Server(2727);
			c.start();
	}
		catch(IOException e){
			e.printStackTrace();
		}

	}
	}
