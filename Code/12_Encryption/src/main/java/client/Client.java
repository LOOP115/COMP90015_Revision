package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONObject;

public class Client {
	
	// IP and port
	private static String ip = "localhost";
	private static int port = 3006;
	
	public static void main(String[] args) {
		try(Socket socket = new Socket(ip, port);){
			// Output and Input Stream
			DataInputStream input = new DataInputStream(socket.getInputStream());
		    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		    
	    	output.writeUTF("I want to connect!");
	    	output.flush();
	    	
    		JSONObject newCommand = new JSONObject();
    		newCommand.put("command_name", "Math");
    		newCommand.put("method_name","multiply");
    		newCommand.put("first_integer",3);
    		newCommand.put("second_integer",2);
    		
    		System.out.println(newCommand.toJSONString());
    		
    		// Read hello from server..
    		String message = input.readUTF();
    		System.out.println(message);
    		
    		// Send  to Server
    		
    		String jsonString = newCommand.toJSONString();

    		//Either of one should be  uncommented 
    		
    		sendEncrypted(jsonString,output);
//    		output.writeUTF(newCommand.toJSONString());
    		
    		output.flush();
    		
    		// Print out results received from server..
    		String result = input.readUTF();
    		System.out.println("Received from server: "+result);
		    
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
		}

	}
	
	private static void sendEncrypted(String message, DataOutputStream output){
		// Encrypt first
		String key = "5v8y/B?D(G+KbPeS";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		try {
			Cipher cipher = Cipher.getInstance("AES");
			// Perform encryption
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(message.getBytes("UTF-8"));
			System.err.println("Encrypted text: "+new String(encrypted));
			output.writeUTF(Base64.getEncoder().encodeToString(encrypted));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String decryptMessage(String message){
		// Decrypt result
		try {
    		String key = "5v8y/B?D(G+KbPeS";
    		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			message = new String(cipher.doFinal(Base64.getDecoder().decode(message.getBytes())));
			System.err.println("Decrypted message: "+message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}

}
