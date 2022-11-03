package dictionary;

import java.rmi.Naming;


public class Client {
	
	public static void main(String[] args) {
		String hostName = "localhost";
		String serviceName = "DictionaryService";
		
		try {
			// DictionaryInterface dict = (DictionaryInterface)Naming.lookup("rmi://"+hostName+"/"+serviceName);
			String serverAddress = "//" +hostName+":"+ "3200" + "/" +serviceName;
			IDictionary dict = (IDictionary) Naming.lookup(serverAddress);
			System.out.println(dict.add("iPad", "Apple Tablet"));
			System.out.println(dict.search("iPad"));
			System.out.println(dict.delete("iPhone"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
