package server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.IRemoteMath;

/**
 * Creates an instance of the RemoteMath class and
 * publishes it in the rmiregistry
 * 
 */
public class RMIServer {

	public static void main(String[] args)  {
		
		try {
			
			//Export the remote math object to the Java RMI runtime so that it
			//can receive incoming remote calls.
			//Because RemoteMath extends UnicastRemoteObject, this
			//is done automatically when the object is initialized.
			//
		    //RemoteMath obj = new RemoteMath();
			//IRemoteMath stub = (IRemoteMath) UnicastRemoteObject.exportObject(obj, 0);
			// 
			IRemoteMath remoteMath = new RemoteMath();
            
            //Publish the remote object's stub in the registry under the name "Compute"
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("MathCompute", remoteMath);
            
            System.out.println("Math server is ready ... Waiting for Connections");
            
            //The server will continue running as long as there are remote objects exported into
            //the RMI runtime, to re	move remote objects from the
            //RMI runtime so that they can no longer accept RMI calls you can use:
           // UnicastRemoteObject.unexportObject(remoteMath, false);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
