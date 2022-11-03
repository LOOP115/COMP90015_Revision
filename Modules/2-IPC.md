# 2 - Interprocess Communication



## 21S1

#### Q5: Consider a server process that has a single TCP server socket, bound and listening on port 4444. For this scenario answer the following: 

(A.) While listening for an incoming TCP connection on port 4444, can the process also receive UDP packets on port 4444? Explain your answer.

?No. For example, in java, TCP uses ServerSocket while UDP uses DatagramSocket, which are two different classes. 

Yes, TCP ports and UDP ports are not related to each other. Can have two different processes binding to the same socket an to the same port but one with UDP and one with TCP.

(B.) While the server is using the TCP connection on port 4444 for sending command messages to the client, can the same server process use another TCP connection on port 2000 for sending control messages to the client? Explain your answer.

Yes. If the server has a different socket that binds to port 2000 connecting client.

(C.) Is it possible for the server process to receive 5 concurrent TCP connections from clients on the same port? Explain your answer.

Yes, as long as the number of connections is smaller than the max length of queue storing the socket connection request.

(D.) Is it possible for a client to connect from TCP port 4444 to the server process? Explain your answer.

If the client and the server are on the same machine, it is not possible to use the same port. If they are on different machines, it is possible.

(E.) To reduce the load of a server process, can another server process be added to the same host with a TCP socket bound on port 4444? Explain your answer.

No, because port 4444 is already in use.



#### Q12.2: Which of the following creates a TCP/IP socket listening on port 123 in Java?

* new Socket(123)
* new ServerSocket(123) ✔
* new DatagramSocket(123)
* None of these



## 16S2

#### Q4: (a) [3 marks] State three differences between UDP and TCP communication.

* TCP is a connection-oriented protocol, whereas UDP is a connectionless protocol.
* The speed for TCP is slower while the speed of UDP is faster.
* TCP uses handshake protocol like SYN, SYN-ACK, ACK while UDP uses no handshake protocols.

#### (b) [2 marks] What is a benefit of XML over JSON format? What is a benefit of JSON over XML format?

* XML is a markup language. It can be used to process and format objects and documents.
* Json is a data format. Json file is much smaller. Json is compact and easy to read. Transferring data using json is much faster.



## Tutorial W3

#### Q1: What kind of application functionality do you think would be suitable for multipoint-to-point and/or multipoint-to-multipoint IPC mechanisms?

* Multipoint-to-point is uncommon in network protocols, however may be seen in application protocols. An example would be logging/push monitoring. Where multiple clients push to a single listener which “ingests” data.
* Multipoint-to-multipoint is uncommon in network protocols, however may be seen in application protocols that do all-all communication. There’s also partial groups where you may see torrenting peers or similar.

For the functionality that you discussed, without such multipoint IPC mechanisms, how many individual messages would need to be sent using multiple point-to-point communications to achieve the same functionality?

* If there are n receivers, the message need to be sent for 5 times.

What is lacking from the multiple point-to-point solution?

* Efficiency.



#### Q2: Wireless broadcast for point-to-multipoint IPC uses something like a wireless base station or a radio transmitter to transmit a signal that is received (practically) simultaneously by all receivers. Such a wireless broadcast mechanism cannot provide multi-cast in this situation. Why not? In what situations could it be provided if any?

* The key difference between broadcast and multicast is that **in the broadcast the packet is delivered to all the host connected to the network whereas, in multicast packet is delivered to intended recipients only**.
* Because the wireless transmission cannot select who receives and who does not.

* However if we use repeaters to reach a greater number of receivers then the repeaters can selectively choose to repeat or not.



#### Q3: Shared memory can be established between more than 2 processes, if those processes are on the same machine, i.e. 3 processes can address the same physical memory location. Is this point-to-multipoint or multipoint-to-point or multipoint-to-multipoint or something else? Justify your answer.

* Shared memory among multiple processes can be thought of as **point-to-multipoint**: a single process writes to a memory address and many processes can read it.
* This depends on the memory access semantics. Naturally, multiple writers to a single byte (smallest addressable unit), will have a sequential nature - if the operations are atomic.
* What if we have multiple bytes? We need additional protocols in order to ensure make it work correctly - who writes/reads where?.



#### Q8: A process running on a machine with a private IP address can initiate communication with a machine on the public Internet if it knows the public IP address of the machine, and the private network is connected to the public Internet via one or more routers. But **how can the machine on the public Internet initiate communication with a machine on the private network?** Note that the private IP address is only unique within the private network that the machine resides, and so many machines in many different private networks may have the same private IP address.

* This is about Network Address Translation or some other technique that routers may use like port forwarding (Virtual Server) to specific local IP addresses based on the port of the incoming packet.



#### Q4-7: Telephone Communication Protocol

#### Q9: client/server socket interaction diagram

#### Q10: FSM that describes the (quite simple) communication protocol employed by the TCP client and server



## Other

#### What is middleware? What is the main purpose of middleware? List 3 middleware technologies that we have learned in class.

* Middleware is a layer of software whose purpose is to mask heterogeneity present in the distributed system.
* Purpose: To provide a convenient programming model to application developers.
* RMI/RPC, Request/Reply Protocol, Marshalling, External data representation (Json)

