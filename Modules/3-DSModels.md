# 3 - DS Models



## 21S1

#### Q2: Discuss any two architectural models for construction of distributed systems. Explain the suitable application scenarios for each.

* Multi-tier Pattern  P47
  * Responsibility is statically allocated.
  * Easy to implement.
  * Security is increased.
  * Application: Social media
* Decentralized Pattern  P48
  * Responsibility is dynamically allocated.
  * Application: P2P file sharing



#### Q12.4: Which of the following is NOT a fundamental model to formally describe common properties of distributed systems?

* Interaction Model
* Failure Model
* Security Model
* Super Model ✔



#### Q12.7: Which one of the following is the best definition of “network latency”?

* The time from the method call of the sending process to the time when the last byte of the message is transferred to the destination.
* The length of the message being transmitted divided by the bandwidth of the host.
* The time from the start of message transmission by the sending process to the beginning of its receipt by the receiving process. ✔
* The number of network hops between the sender and the receiver.



#### Q12.10: Which of the following does not provide an overlay network?

* Client-server socket applications
* Peer-to-peer file sharing systems (such as Bit torrent)
* A virtual private network (VPN) ✔?
* Skype peer-to-peer application



## 17S1

#### Q2: [5 marks] For each of the following architectural designs, provide a definition and give a reason for and a reason against the use of the design. Draw a diagram to illustrate the architecture.

##### (a) Client / Server	P44

* There is a single server which process requests from multiple clients.
* Pro: Easy to implement
* Con: SIngle point of failure / Scalability bottleneck

##### (b) Peer-to-Peer	P48

* Every peer directly communicate with every other peer.
* Pro: Good scalability / Robust to peer failure
* Con: Privacy / security concerns

##### (c) Multi-server	P45

* There is a server cluster and all servers in the cluster provide the same services.
* Pro: Client can communicate with any of server in the cluster. / Failure of single server does not lead to system failure.
* Con: More difficult to implement compare to Client / Server pattern. / All servers do the same thing, which is not efficient since some servers may be idle.

##### (d) Proxy server	P49

* Proxy server is an extra layer between client and server. It receives client's requests and send them to the server on behalf of clients. Then they send the results received from server back to the client.
* Pro: Increase security since clients never communicate directly with servers. / At server side can relay requests to appropriate servers.
* Con: There may be a high load on the proxy server.

##### (e) Thin-client

* Not included in this semester.
* A thin client is a computer that runs from resources stored on a central server instead of a localized hard drive. Thin clients work by **connecting remotely to a server-based computing environment where most applications, sensitive data, and memory, are stored**.



## 16S2

#### Q1: (b) [5 marks] Consider the provisioning of general purpose applications in the cloud, where a web browser is used to connect to and operate the application. For example, an instance of a spreadsheet application can run on the cloud and a user can connect to it via their web browser to work on spreadsheet data. What type of distributed system architectural model best describes this? Give a reason for your answer. Give three reasons that support the use of this architectural model. Give an example of an application for which the architectural model is not suitable.

Likely to be Thin-client.



#### Q3: (b) [2 marks] What is meant by a timing failure? Briefly explain an example of a distributed system where a timing failure would have significant impact.

There is error with the system's local clock and the real time. P150



## 15S2

#### Q2: (a) What is an architectural model? In your explanation include the important aspects or steps when developing an architectural model.

L3 P4

#### (b) [3 marks] Name and briefly explain the role of each of the three fundamental models used for analyzing the properties of distributed systems.

P40

#### (c) [3 marks] List three types of communication omission failures. For each type write a sentence that explains the failure.

P149



## Other

#### In the failure model of a distributed system, there are 3 types of failures. Briefly describe these 3 types of failures. Which type of failure do you think is the most difficult to deal with, and why?

Failure Model	P149

* Fail-stop
* Crash
* Omission
  * Send-omission
  * Receive-omission
* Arbitrary

Arbitrary is the most difficult since any unexpected error may lead to this failure.

Timing Failures	P150

