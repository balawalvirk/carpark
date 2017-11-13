import java.io.IOException;
import java.util.Set;

public class UIClientBridge implements Runnable {

	public static String carSpaceAction;
    public static volatile String noOfCarSpacesAvailableOnFloor1 = "";
    public static volatile String noOfCarSpacesAvailableOnFloor2 = "";
    
    
	public UIClientBridge(String keyValue) {
		carSpaceAction = keyValue;
	    try {
			Entrance1.main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UIClientBridge(String noOfSpacesAvailable_String, double floorNo) {
		if(floorNo == 1)
			setAvaiableCarParkSpacesFloor1(noOfSpacesAvailable_String, floorNo);
		if(floorNo == 2)
			setAvaiableCarParkSpacesFloor2(noOfSpacesAvailable_String, floorNo);
	}
	
	public UIClientBridge() {
		//lazy instantiation...
	}
	
	public String getCarSpaceAction() {
		return carSpaceAction;
	}
	
	
	public void setAvailableCarParkSpacesFloor1(String availableSpaces) {
		noOfCarSpacesAvailableOnFloor1 = availableSpaces;
		startThread(1);
	}
	
	public void setAvaiableCarParkSpacesFloor1(String availableSpaces_String, double availableSpaces_double) {
		noOfCarSpacesAvailableOnFloor1 = availableSpaces_String;
	}
	
	public String getNoOfSpacesAvaiableOnFloor1() {
		return noOfCarSpacesAvailableOnFloor1;
	}

	
	public void setAvailableCarParkSpacesFloor2(String availableSpaces) {
		noOfCarSpacesAvailableOnFloor2 = availableSpaces;
		startThread(2);
	}
	
	public void setAvaiableCarParkSpacesFloor2(String availableSpaces_String, double availableSpaces_double) {
		noOfCarSpacesAvailableOnFloor2 = availableSpaces_String;
	}
	
	public String getNoOfSpacesAvaiableOnFloor2() {
		return noOfCarSpacesAvailableOnFloor2;
	}
	
	private void startThread(double floorNo) {
		Thread thread = null;
		
		if(floorNo == 1)
			thread = new Thread(new UIClientBridge(getNoOfSpacesAvaiableOnFloor1(), floorNo));
		if(floorNo == 2)
			thread = new Thread(new UIClientBridge(getNoOfSpacesAvaiableOnFloor2(), floorNo));
		
		thread.start();
	}
	
	public void run() {
		noOfCarSpacesAvailableOnFloor1 = getNoOfSpacesAvaiableOnFloor1();
		noOfCarSpacesAvailableOnFloor2 = getNoOfSpacesAvaiableOnFloor2();
	}
}
