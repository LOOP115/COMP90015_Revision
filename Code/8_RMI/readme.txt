
Note: Please make sure your java installation's bin directory is in your system path (to execute rmiregistry across system).

1. Start the rmiregistry by using the command:
	
	Unix/Linux-> 	rmiregistry [port]  (specifying port is optional, default works on port 1099)
	Windows ->	 start rmiregistry	
	
	Note: the above command should be run at the bin directory of your project, or else explicit 
			security policy need to be set. (Example @ -> "C:\RMI\RMIDemo\rmidemo\bin\")
	
	
  
   This will start a remote object registry on the specified port on the current host.
   
2. Start the RMI server by running the RMIServer class with the following VM arguments:
		-Djava.rmi.server.codebase=file:<yourProjectLocation>/RMIMathServerExample/bin/
		
		e.g.
		-Djava.rmi.server.codebase=file:"C:\RMI\RMIDemo\rmidemo\bin\"

3. Start the client by running the MathClient class with the following VM arguments:
		-Djava.rmi.server.codebase=file:<yourProjectLocation>/RMIMathServerExample/bin/
		e.g. 
	(linux)	-Djava.rmi.server.codebase=file:/C:/Users/memorylake/demoworkspace/RMIDemo/bin/
		
	(windows)	-Djava.rmi.server.codebase=file:"C:\RMI\RMIDemo\rmidemo\bin\"


