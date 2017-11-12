import java.io.IOException;
import java.util.Set;

public class UIClientBridge implements Runnable {

	public static String carSpaceAction;
    public static volatile String noOfCarSpacesAvailable = "";
    
	public UIClientBridge(String keyValue) {
		carSpaceAction = keyValue;
		String[] arguments = new String[] {"123"};
	    try {
			Entrance1.main(arguments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UIClientBridge(String noOfSpacesAvailable_String, double noOfSpacesAvailable_double) {
		setAvaiableCarParkSpaces(noOfSpacesAvailable_String, noOfSpacesAvailable_double);
	}
	
	public UIClientBridge() {
		//lazy instantiation...
	}
	
	public String getCarSpaceAction() {
		return carSpaceAction;
	}
	
	public void setAvaiableCarParkSpaces(String availableSpaces) {
		noOfCarSpacesAvailable = availableSpaces;
		startThread();
		//System.out.println("In UIBridge" + noOfCarSpacesAvailable);
	}
	
	public void setAvaiableCarParkSpaces(String availableSpaces_String, double availableSpaces_double) {
		noOfCarSpacesAvailable = availableSpaces_String;
	}
	
	public String getNoOfSpacesAvaiable() {
		return noOfCarSpacesAvailable;
	}

	private void startThread() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
		
		Thread thread = new Thread(new UIClientBridge(getNoOfSpacesAvaiable(), 0));
		for(int i=0; i<threadArray.length; i++) {
			threadArray[i].interrupt();
		}
		thread.start();
	}
	
	public void run() {
		noOfCarSpacesAvailable = getNoOfSpacesAvaiable();
		while(true) {
			System.out.println("From the thread "+getNoOfSpacesAvaiable());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("thread interrupted");
				return;
			}
		}
	}
}
