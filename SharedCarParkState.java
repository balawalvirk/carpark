import java.net.*;
import java.io.*;

public class SharedCarParkState{
	
	private SharedCarParkState mySharedObj;
	private String myThreadName;
	private double availableCarSpace;
	private boolean accessing=false; // true a thread has a lock, false otherwise
	private int threadsWaiting=0; // number of waiting writers
	
	
   // Constructor	
	
	public SharedCarParkState(double SharedVariable) {
		availableCarSpace = SharedVariable;
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
    		//System.out.println(myThreadName + " received "+ theInput);
    		String theOutput = null;
    		
    		// Check what the client said
    		
    		if (theInput.equalsIgnoreCase("In")) { // car entering the car park
    			if(availableCarSpace == 0) {
    				theOutput = "Car park is currently full. Please wait until someone leaves. Thank you!";
    			}
    			else if (myThreadName.equals("CarParkServerThread1") || myThreadName.equals("CarParkServerThread2")
    					|| myThreadName.equals("CarParkServerThread3") || myThreadName.equals("CarParkServerThread4")) {
    				// subtract 1 car
    				availableCarSpace = availableCarSpace - 1;
   				//System.out.println(myThreadName + " made the SharedVariable " + availableCarSpace);
    				theOutput = "Incoming car request - request completed.  Car spaces available now = " + availableCarSpace;
    			}
   			else {System.out.println("Error - thread call not recognised.");}
    		}
    		
    		else if (theInput.equalsIgnoreCase("Out")) { //car leaving the car park
    			if(availableCarSpace == 40) {
    				theOutput = "Error - out of range scenario";
    			}
    			else if (myThreadName.equals("CarParkServerThread1") || myThreadName.equals("CarParkServerThread2")
    					|| myThreadName.equals("CarParkServerThread3") || myThreadName.equals("CarParkServerThread4")) {
    				// add 1 car
    				availableCarSpace = availableCarSpace + 1;
   				//System.out.println(myThreadName + " made the SharedVariable " + availableCarSpace);
    				theOutput = "Outgoing car request - request completed.  Car spaces available now = " + availableCarSpace;
    			}
   			else {System.out.println("Error - thread call not recognised.");}
    		}
    		
    		else { //incorrect request
    			theOutput = myThreadName + " received incorrect request - only understands \"In\" or \"Out\" commands";
    		}
 
     	//Return the output message to the CarParkServer
    		return new String[] {theOutput, String.valueOf(availableCarSpace)};
    	}
}

