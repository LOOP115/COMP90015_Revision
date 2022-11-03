package remote;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI Remote interface - must be shared between client and server.
 * All methods must throw RemoteException.
 * All parameters and return types must be either primitives or Serializable.
 *  
 * Any object that is a remote object must implement this interface.
 * Only those methods specified in a "remote interface" are available remotely.
 */
public interface IRemoteMath extends Remote {

	public double add(double a, double b) throws RemoteException;
	
	public double subtract(double a, double b) throws RemoteException;
	
	public double mul(double a, double b) throws RemoteException;
	
	public double div(double a, double b) throws RemoteException;
	
}
