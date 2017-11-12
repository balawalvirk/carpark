import java.io.*;
import java.net.*;

public class Exit1 {
    public static void main(String[] args) throws IOException {

        // Set up the socket, in and out variables

        Socket CarParkClientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int CarParkSocketNumber = 4545;
        String CarParkServerName = "localhost";
        String Exit1 = "Exit1";

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

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        System.out.println("Initialised " + Exit1 + " client and IO connections");
        
        // This is modified as it's the client that speaks first

        while (true) {
            
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                System.out.println(Exit1 + " sending " + fromUser + " to CarParkServer");
                out.println(fromUser);
            }
            fromServer = in.readLine();
            System.out.println(Exit1 + " received " + fromServer + " from CarParkServer");
        }
            
        
       // Tidy up - not really needed due to true condition in while loop
      //  out.close();
       // in.close();
       // stdIn.close();
       // CarParkClientSocket.close();
    }
}
