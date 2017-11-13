
public class test {
	
	public double a = 20;
	public static double availableCarSpace2 = 20;
	
	public static void main(String[] args) {
		test s = new test();
	}
	
	test(){
		//System.out.println(b());
		System.out.println(getAvailableCarSpace());
		
		UIClientBridge uiClientBridge = new UIClientBridge();
	}
	
	public double b() {
		a=a+20;
		return a;
	}
	
	public double c() {
		a=a+20;
		return a;
	}
	
	public static void setAvailableCarSpace(double availableCarSpace){
		availableCarSpace2 = availableCarSpace;
	}

	public static double getAvailableCarSpace(){
		return availableCarSpace2;
	}
}
