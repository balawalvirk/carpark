import java.net.*;
import java.io.*;

public class CarParkServer {
  public static void main(String[] args) throws IOException {

	ServerSocket CarParkServerSocket = null;
    boolean listening = true;
    String CarParkServerName = "CarParkServer";
    int CarParkServerNumber = 4000;
    
    double SharedVariable = 40;

    //Create the shared object in the global scope...
    SharedCarParkState ourCarParkStateObject = new SharedCarParkState(SharedVariable);
        
    // Make the server socket

    try {
      CarParkServerSocket = new ServerSocket(CarParkServerNumber);
    } catch (IOException e) {
      System.err.println("Could not start " + CarParkServerName + " specified port.");
      System.exit(-1);
    }
    System.out.println(CarParkServerName + " started");

    //Got to do this in the correct order with only four clients!  Can automate this...
    
    while (listening){
      new CarParkServerThread(CarParkServerSocket.accept(), "CarParkServerThread1", ourCarParkStateObject).start();
      new CarParkServerThread(CarParkServerSocket.accept(), "CarParkServerThread2", ourCarParkStateObject).start();
      new CarParkServerThread(CarParkServerSocket.accept(), "CarParkServerThread3", ourCarParkStateObject).start();
      new CarParkServerThread(CarParkServerSocket.accept(), "CarParkServerThread4", ourCarParkStateObject).start();
      System.out.println("New " + CarParkServerName + " thread started.");
    }
    CarParkServerSocket.close();
  }
}