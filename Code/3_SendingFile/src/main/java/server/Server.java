package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import javax.net.ServerSocketFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Server {
	
	// Declare the port number
	private static int port = 3003;
	
	// Identifies the user number connected
	private static int counter = 0;

	public static void main(String[] args) {
		ServerSocketFactory factory = ServerSocketFactory.getDefault();
		try(ServerSocket server = factory.createServerSocket(port)){
			System.out.println("Waiting for client connection..");
			
			// Wait for connections.
			while(true){
				Socket client = server.accept();
				counter++;
				System.out.println("Client "+counter+": Applying for connection!");
				
				
				// Start a new thread for a connection
				Thread t = new Thread(() -> serveClient(client));
				t.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	private static void serveClient(Socket client){
		try(Socket clientSocket = client){
			
			// The JSON Parser
			JSONParser parser = new JSONParser();
			// Input stream
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			// Output Stream
		    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
		    System.out.println("CLIENT: "+input.readUTF());
		   
		    output.writeUTF("Server: Hi Client "+counter+" !!!");
		    
		    // Receive more data..
		    while(true){
		    	if(input.available() > 0){
		    		// Attempt to convert read data to JSON
		    		JSONObject command = (JSONObject) parser.parse(input.readUTF());
		    		System.out.println("COMMAND RECEIVED: "+command.toJSONString());
		    		parseCommand(command,output);
		    	

		    	}
		    }
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	private static void  parseCommand(JSONObject command, DataOutputStream output) {
		
		// This section deals with the file handler
		if(command.get("command_name").equals("GET_FILE")){
			String fileName = (String) command.get("file_name");
			// Check if file exists
			File f = new File("server_files/"+fileName);
			if(f.exists()){
				// Send this back to client so that they know what the file is.
				JSONObject trigger = new JSONObject();
				trigger.put("command_name", "SENDING_FILE");
				trigger.put("file_name","sauron.jpg");
				trigger.put("file_size",f.length());
				try {
					// Send trigger to client
					output.writeUTF(trigger.toJSONString());
					
					// Start sending file
					RandomAccessFile byteFile = new RandomAccessFile(f,"r");
					byte[] sendingBuffer = new byte[1024*1024];
					int num;
					// While there are still bytes to send..
					while((num = byteFile.read(sendingBuffer)) > 0){
						System.out.println("value:" + num);
						
						output.write(Arrays.copyOf(sendingBuffer, num));
					}
					byteFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				// Throw an error here..
			}
		}
		// TODO Auto-generated method stub
		
	}

}
