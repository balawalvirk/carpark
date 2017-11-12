
import java.net.*;
import java.io.*;


public class CarParkServerThread extends Thread {

	
  private Socket actionSocket = null;
  private SharedCarParkState myCarParkStateObject;
  private String myCarParkServerThreadName;
  private double mySharedVariable;
   
  //Setup the thread
  	public CarParkServerThread(Socket actionSocket, String CarParkServerThreadName, SharedCarParkState SharedObject) {
	
//	  super(CarParkServerThreadName);
	  this.actionSocket = actionSocket;
	  myCarParkStateObject = SharedObject;
	  myCarParkServerThreadName = CarParkServerThreadName;
	}

  public void run() {
    try {
      System.out.println(myCarParkServerThreadName + " initialising.");
      PrintWriter out = new PrintWriter(actionSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(actionSocket.getInputStream()));
      String inputLine;
	  String[] outputLine;
      
      while ((inputLine = in.readLine()) != null) {
	    	  // Get a lock first
	    	  try { 
	    		  myCarParkStateObject.acquireLock();  
	    		  outputLine = myCarParkStateObject.processInput(myCarParkServerThreadName, inputLine);
	    		  out.println(outputLine[0] + " : " + outputLine[1]);
	    		  myCarParkStateObject.releaseLock();
	    	  }
	    	  catch(InterruptedException e) {
	    		  System.err.println("Failed to get lock when reading:"+e);
	    	  }
      }
      
       out.close();
       in.close();
       actionSocket.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}