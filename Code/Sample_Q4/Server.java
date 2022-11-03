package dictionary;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;


public class Server extends UnicastRemoteObject implements IDictionary{
	
	static String fileName; 
	static ConcurrentHashMap<String, String> dict = new ConcurrentHashMap<>();
	
	protected Server() throws RemoteException {
		super();
	}
	
	// Read file and read each line as word,meaning and add to the hashmap
	public static void readDictionary() throws IOException {
		File file = new File(fileName);
		if(file.isFile() && file.exists()) {
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			
			try {
				inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
				bufferedReader = new BufferedReader(inputStreamReader);
			} catch (FileNotFoundException e) {
				System.out.println("File not found, or unsupported encoding type!");
			}
			
			String line;
	
			while(true) {
				assert bufferedReader != null;
				if ((line = bufferedReader.readLine()) == null) break;
				String[] lines = line.split(":");
				dict.put(lines[0], lines[1]);
				
			}
			
			inputStreamReader.close();
			bufferedReader.close();
		}
	}
	
	// Write the hashmap to the dictionary.txt file
	public static void writeDictionary() {
		File file = new File(fileName);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for(Entry<String, String> entry: dict.entrySet()) {
				writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot write to file " + file.getName() + ".");
		}
	}
	
	@Override
	public String add(String text, String meaning) throws RemoteException {
		// TODO Auto-generated method stub
		if(dict.get(text) != null) {
			return text+ " already in the dictionary!";
		}else {
			dict.put(text, meaning);
			writeDictionary(); return text + " added successfully"; 
		}
	}

	@Override
	public String search(String text) throws RemoteException {
		if(dict.get(text) == null) {
			return "Not in dictionary";
		}
		return dict.get(text);
	}

	@Override
	public String delete(String text) throws RemoteException {
		if(dict.get(text) == null) {
			return text + " not found!";
		}
		dict.remove(text);
		writeDictionary();
		return text + " successfully deleted";
	}
	
	public static void main(String[] args) {
		String serviceName = "DictionaryService";
		fileName = "dict.txt";
		
		try {
			DictionaryInterface server = new DictionaryServer();
			// Naming.rebind("rmi://"+hostName+"/"+serviceName, server);
			Registry registry = LocateRegistry.createRegistry(Integer.parseInt("3200"));
			registry.bind(serviceName, server);
			System.out.println("Dictionary is running...");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
