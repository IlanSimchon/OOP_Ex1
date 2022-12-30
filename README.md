# OOP_Ex1
## UndoableStringBuilder improvement using Observer pattern

In this project, we will use the undoablestringbuilder class that we built in the past, which contains the stringbuilder class with the option to undo (Memento Pattern), in this project we will add the observer design pattern to the class and with it we will be able to update a list of potential customers with every change made in the database

**The project contains several parts:**
- **groupAdmin class** (which implements Sender) which contains stringbuilder and is defined as the manager of the database, every time a change is made in the database it sends an update to all the members registered in the list of updates.

This class contains the database and a database of registered customers.

- **ConcreteMember** class (which implements Member) and is defined as a client that receives the updates from the database manager.

This class contains a copy of the database (sallow copy) and the name of the customers.

- **Tests class** that contains tests for all the functions of the classes and also contains tests that monitor the change in the size of the JVM's memory during the program's run


### Observer pattern overview
![image](https://user-images.githubusercontent.com/98847692/210052659-b663a6d8-4511-4d62-8b0e-30555b49b362.png)

In software design and engineering, the observer pattern is a software design pattern in which an object, named the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.

It is often used for implementing distributed event-handling systems in event-driven software. In such systems, the subject is usually named a "stream of events" or "stream source of events" while the observers are called "sinks of events." The stream nomenclature alludes to a physical setup in which the observers are physically separated and have no control over the emitted events from the subject/stream source. This pattern thus suits any process by which data arrives from some input that is not available to the CPU at startup, but instead arrives seemingly at random (HTTP requests, GPIO data, user input from peripherals, distributed databases and blockchains, etc.).

Most modern programming languages comprise built-in event constructs implementing the observer-pattern components. While not mandatory, most observer implementations use background threads listening for subject events and other support mechanisms provided by the kerne
