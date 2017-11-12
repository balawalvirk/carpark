import java.io.*;
import java.net.*;

public class Entrance1 {
	
    public static void main(String[] args) throws IOException {

        // Set up the socket, in and out variables

        Socket CarParkClientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int CarParkSocketNumber = 4000;
        String CarParkServerName = "localhost";
        String Entrance1 = "Entrance1";
        
        try {
            CarParkClientSocket = new Socket(CarParkServerName, CarParkSocketNumber);
            out = new PrintWriter(CarParkClientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(CarParkClientSocket.getInputStream()));
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+ CarParkSocketNumber);
            System.exit(1);
        }

        String carSpaceActivity = UIClientBridge.carSpaceAction;
        BufferedReader stdIn = new BufferedReader(new StringReader(carSpaceActivity));
        
        String fromServer;
        String fromUser;

        System.out.println("Initialised " + Entrance1 + " client and IO connections");
        
        
        fromUser = stdIn.readLine();
        if (fromUser != null) {
            System.out.println(Entrance1 + " sending " + fromUser + " request to CarParkServer");
            out.println(fromUser);
        }
        fromServer = in.readLine().toString();
        System.out.println(Entrance1 + " received " + fromServer + " : from CarParkServer");
        

    		UIClientBridge uiClientBridge = new UIClientBridge();
    		String[] s = fromServer.split(":");
    		String availableCarParkSpace = s[1];
        uiClientBridge.setAvaiableCarParkSpaces(availableCarParkSpace);
        
       // Tidy up - not really needed due to true condition in while loop
      //  out.close();
       // in.close();
       // stdIn.close();
       // CarParkClientSocket.close();
    }
}
