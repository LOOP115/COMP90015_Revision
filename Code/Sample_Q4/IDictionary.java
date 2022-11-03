package dictionary;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IDictionary extends Remote {
	
	String add(String text, String meaning) throws RemoteException;
	String search(String text) throws RemoteException;
	String delete(String text) throws RemoteException;

}
