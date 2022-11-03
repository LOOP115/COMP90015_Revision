package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import remote.IRemoteMath;

/**
 * Server side implementation of the remote interface.
 * Must extend UnicastRemoteObject, to allow the JVM to create a 
 * remote proxy/stub.
 *
 */
public class RemoteMath extends UnicastRemoteObject implements IRemoteMath {

	/**
	 * Added 
	 */
	//private static final long serialVersionUID = 1L;
	private int numberOfComputations;
	
	protected RemoteMath() throws RemoteException {
		numberOfComputations = 0;

	}

	@Override
	public double add(double a, double b) throws RemoteException {
		numberOfComputations++;
		System.out.println("Number of computations performed so far = " 
				+ numberOfComputations);
		return (a+b);

	}

	@Override
	public double subtract(double a, double b) throws RemoteException {
		numberOfComputations++;
		System.out.println("Number of computations performed so far = " 
				+ numberOfComputations);
		return (a-b);

	}
	public double mul(double a, double b) throws RemoteException{
		numberOfComputations++;
		System.out.println("Number of computations performed so far = " 
				+ numberOfComputations);
		return (a*b);

	}
	
	public double div(double a, double b) throws RemoteException{
		numberOfComputations++;
		System.out.println("Number of computations performed so far = " 
				+ numberOfComputations);
		return (a/b);

	}
	
}
