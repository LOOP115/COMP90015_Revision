# 4 - Threads



## 21S1

#### Q4: What are the advantages of using multiple threads over multiple processes? Explain the difference between a worker pool thread model and a thread-per-object model, including details concerning what concurrency control and queueing is required in each case.

Threads require much fewer resources than processes and are quick to create. Multiple processes require a huge amount of time and resources to create. 

Threads have a shared memory while processes require OS to allocate address space, memory, file and IO.

* Worker pool thread model  P34
  * The thread-pool paradigm creates a fixed or dynamically resizable set of threads that either take incoming socket connection requests from a process maintained queue, or, if the accept socket API is thread safe then the pool of threads can take directly from the OS queue.
  * Need concurrency control to accept socket requests. Constraint by number of worker threads.
  * Worker threads are maintained in a queue. Once a thread finished its task, it will be pushed to the queue to wait for new tasks.
*  Thread-per-object model  P37
  * The thread-per-object paradigm creates a thread for each data object. This can potentially reduce cache miss rates in the machine since for a given data object, only 1 thread ever accesses it and it will reside only in the cache for that thread. This can greatly increase cache efficiency which can significantly improve overall performance of the machine.
  * Concurrency control is not needed.
  * Data objects are maintained in queues.



#### Q12.3: In Java Threads, which of the following methods execute threads without blocking?

* Thread.run() ✔
* Thread.join()
* Thread.start()
* Thread.interrupt()



#### Q12.6 Which of the following is not true?

* A new thread is spawned via the start method of class Thread.
* Two threads can simultaneously execute a synchronized non-static method of different instances of the same Java class.
* Two threads can simultaneously execute a synchronized non-static method of the same instance of the same Java class. ✔
* The sleep method of the class Thread is static and it puts the "current" thread to sleep.


