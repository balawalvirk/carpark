import java.net.*;
import java.io.*;

public class SharedCarParkState{
	
	private SharedCarParkState mySharedObj;
	private String myThreadName;
	private double floor1AvailableCarSpace;
	private double floor2AvailableCarSpace;
	private boolean accessing=false; // true a thread has a lock, false otherwise
	private int threadsWaiting=0; // number of waiting writers
	
	
   // Constructor	
	
	public SharedCarParkState(double floor1Variable, double floor2Variable) {
		floor1AvailableCarSpace = floor1Variable;
		floor2AvailableCarSpace = floor2Variable;
	}

   //Attempt to aquire a lock
	
    public synchronized void acquireLock() throws InterruptedException {
        Thread me = Thread.currentThread(); // get a ref to the current thread
        //System.out.println(me.getName()+" is attempting to acquire a lock!");	
        ++threadsWaiting;
	    while (accessing) {  // while someone else is accessing or threadsWaiting > 0
	      System.out.println(me.getName()+" waiting to get a lock as someone else is accessing...");
	      //wait for the lock to be released - see releaseLock() below
	      wait();
	    }
	    // nobody has got a lock so get one
	    --threadsWaiting;
	    accessing = true;
	    //System.out.println(me.getName()+" got a lock!"); 
	 }

	 // Releases a lock to when a thread is finished
	  
	 public synchronized void releaseLock() {
	     //release the lock and tell everyone
	     accessing = false;
	     notifyAll();
	     Thread me = Thread.currentThread(); // get a ref to the current thread
	     //System.out.println(me.getName()+" released a lock!");
	 }
	
	
    /* The processInput method */

	public synchronized String[] processInput(String myThreadName, String theInput) {
    		String theOutput = null;
    		
    		// Check what the client said
    		
    		if (theInput.equalsIgnoreCase("InFloor1")) { // car entering floor 1
    			if (myThreadName.equals("CarParkServerThread1") || myThreadName.equals("CarParkServerThread2")
    					|| myThreadName.equals("CarParkServerThread3") || myThreadName.equals("CarParkServerThread4")) {
    				// subtract 1 car from floor 1
    				floor1AvailableCarSpace = floor1AvailableCarSpace - 1;
    				theOutput = "\"Incoming car request to floor 1 completed\".  Car spaces on floor 1 available now = " + floor1AvailableCarSpace;
    			}
   			else {System.out.println("Error - thread call not recognised.");}
    		}
    		
    		else if (theInput.equalsIgnoreCase("OutFloor1")) { //car leaving the car park
    			if (myThreadName.equals("CarParkServerThread1") || myThreadName.equals("CarParkServerThread2")
    					|| myThreadName.equals("CarParkServerThread3") || myThreadName.equals("CarParkServerThread4")) {
    				// add 1 car space
    				floor1AvailableCarSpace = floor1AvailableCarSpace + 1;
    				theOutput = "\"Outgoing car request completed\".  Car spaces on floor 1 available now = " + floor1AvailableCarSpace;
    			}
   			else {System.out.println("Error - thread call not recognised.");}
    		}
    		
    		else if (theInput.equalsIgnoreCase("InFloor2")) { // car entering floor 2
    			if (myThreadName.equals("CarParkServerThread1") || myThreadName.equals("CarParkServerThread2")
    					|| myThreadName.equals("CarParkServerThread3") || myThreadName.equals("CarParkServerThread4")) {
    				// subtract 1 car from floor 2
    				floor2AvailableCarSpace = floor2AvailableCarSpace - 1;
    				theOutput = "\"Incoming car request to floor 2 completed\".  Car spaces on floor 2 available now = " + floor2AvailableCarSpace;
    			}
   			else {System.out.println("Error - thread call not recognised.");}
    		}
 
    		else if (theInput.equalsIgnoreCase("OutFloor2")) { //car leaving the car park
    			if (myThreadName.equals("CarParkServerThread1") || myThreadName.equals("CarParkServerThread2")
    					|| myThreadName.equals("CarParkServerThread3") || myThreadName.equals("CarParkServerThread4")) {
    				// add 1 car space
    				floor2AvailableCarSpace = floor2AvailableCarSpace + 1;
    				theOutput = "\"Outgoing car request completed\".  Car spaces on floor 2 available now = " + floor2AvailableCarSpace;
    			}
   			else {System.out.println("Error - thread call not recognised.");}
    		}
    		
     	//Return the output message to the CarParkServer
    		return new String[] {theOutput, String.valueOf(floor1AvailableCarSpace), String.valueOf(floor2AvailableCarSpace)};
    	}
}

