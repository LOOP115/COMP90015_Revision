package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;

public class Client {
	
	// IP and port
	private static String ip = "localhost";
	private static int port = 3002;
	
	public static void main(String[] args) {
		try(Socket socket = new Socket(ip, port);){
			// Output and Input Stream
			DataInputStream input = new DataInputStream(socket.getInputStream());
		    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		    
	    	output.writeUTF("I want to connect!");
	    	output.flush();
	    	
    		JSONObject newCommand = new JSONObject();
    		
    		newCommand.put("command_name", "Math");
    		newCommand.put("method_name","add");
    		newCommand.put("first_integer",1);
    		newCommand.put("second_integer",1);
    		
    		System.out.println(newCommand.toJSONString());
    		
    		// Read hello from server..
    		String message = input.readUTF();
    		System.out.println(message);
    		
    		// Send message to Server
    		output.writeUTF(newCommand.toJSONString());
    		output.flush();
    		
    		// Print out results received from server..
    		String result = input.readUTF();
    		System.out.println("Received from server: "+result);
		    
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
		}

	}

}
