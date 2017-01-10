import java.io.DataInputStream;
import java.util.Scanner;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
public class Client{

	public static void main(String[] args) {
		
		Scanner oScan = new Scanner(System.in);
		
		try {
			Socket clientSocket = new Socket("localhost",2727);
			String prompt = "";
			while(prompt != ".." || prompt != ".."){	
			//prompts the client to respond

 			System.out.println("Message the server below:");
			prompt = oScan.nextLine();
	        new DataOutputStream(clientSocket.getOutputStream()).writeUTF("Client: " + prompt);
			
	        //listens for the servers response
	        String in = new DataInputStream(clientSocket.getInputStream()).readUTF();
	        	//I'm not sure why it adds on 8 at the end of each line but -8 from it makes it count it perfectly :)
	        System.out.println(in + "\t\t\tLength: " + (in.length()-8));
			}
	        clientSocket.close();		
		} 
		
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
