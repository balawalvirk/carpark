import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI extends JPanel implements KeyListener {

	private static final String DISPLAY_TEXT1 = "Spaces available on floor 1 = ";
	private static final String DISPLAY_TEXT2 = "Spaces available on floor 2 = ";
	private static final int TEXT_INDENT = 50;
	
    private double spacesAvailableOnFloor1_double = 20;
	private static String spacesAvailableOnFloor1;
	private double spacesAvailableOnFloor2_double = 20;
	private static String spacesAvailableOnFloor2;
	
	private boolean isParkingSpacesVisible = false;
    
	
	public static void main(String[] s) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new UI());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
	
    public UI() {
        this.setPreferredSize(new Dimension(800, 500));
        addKeyListener((KeyListener) this);
        this.setBackground(Color.LIGHT_GRAY);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    public void paintComponent(Graphics g) {
        updateVariables();
        invalidCases();
        drawString(g);
    }

    public void drawString(Graphics g) {
        int textIndent = g.getFontMetrics().stringWidth(DISPLAY_TEXT1) + 40;
        
    		g.setFont(new Font("Arial", Font.PLAIN, 17));
        g.drawString(DISPLAY_TEXT1, TEXT_INDENT, this.getHeight()/2);
        g.setFont(new Font("Arial", Font.BOLD, 17));
        g.drawString(spacesAvailableOnFloor1, textIndent + TEXT_INDENT, this.getHeight()/2);
        
        g.setFont(new Font("Arial", Font.PLAIN, 17));
        g.drawString(DISPLAY_TEXT2, TEXT_INDENT, (this.getHeight()/2)+50);
        g.setFont(new Font("Arial", Font.BOLD, 17));
        g.drawString(spacesAvailableOnFloor2, textIndent + TEXT_INDENT, (this.getHeight()/2)+50);
    }
    
    public void keyTyped(KeyEvent e) {
    	//floor 1
    		if(e.getKeyChar() == 'a' && spacesAvailableOnFloor1_double > 0) {
    			isParkingSpacesVisible = true;
    			UIClientBridge uiClientBridge = new UIClientBridge("InFloor1", "entrance1");
    		}
    		else if(e.getKeyChar() == 's' && spacesAvailableOnFloor1_double < 20) {
    			isParkingSpacesVisible = true;
    			UIClientBridge uiClientBridge = new UIClientBridge("OutFloor1", "entrance2");
    		}
    		
    	//floor2
    		else if(e.getKeyChar() == 'q' && spacesAvailableOnFloor2_double > 0) {
    			isParkingSpacesVisible = true;
    			UIClientBridge uiClientBridge = new UIClientBridge("InFloor2", "exit1");
    		}
    		else if(e.getKeyChar() == 'w' && spacesAvailableOnFloor2_double < 20) {
    			isParkingSpacesVisible = true;
    			UIClientBridge uiClientBridge = new UIClientBridge("OutFloor2", "exit2");
    		}
        repaint();
    }
    
    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    
    private void updateVariables() {
    		if(isParkingSpacesVisible) {
    			spacesAvailableOnFloor1 = UIClientBridge.noOfCarSpacesAvailableOnFloor1;
			spacesAvailableOnFloor1_double = Double.parseDouble(spacesAvailableOnFloor1);
			spacesAvailableOnFloor2 = UIClientBridge.noOfCarSpacesAvailableOnFloor2;
			spacesAvailableOnFloor2_double = Double.parseDouble(spacesAvailableOnFloor2);
    		}
    		else {
    			spacesAvailableOnFloor1 = "Please press \"A\" for IN or \"S\" for OUT";
    			spacesAvailableOnFloor2 = "Please press \"Q\" for IN or \"W\" for OUT";
    			//random number to make if statements work in the keyTyped method for the first time
    			spacesAvailableOnFloor1_double = 10;
    			spacesAvailableOnFloor2_double = 10;
    		}
    }
    
    private void invalidCases() {
    		if(spacesAvailableOnFloor1_double == 20)
    			spacesAvailableOnFloor1 = "20 (Floor 1 empty!)";
    		if(spacesAvailableOnFloor1_double == 0)
    			spacesAvailableOnFloor1 = "0 (Floor 1 full!)";
    		if(spacesAvailableOnFloor2_double == 20)
    			spacesAvailableOnFloor2 = "20 (Floor 2 empty!)";
    		if(spacesAvailableOnFloor2_double == 0)
    			spacesAvailableOnFloor2 = "0 (Floor 2 full!)";
    }
}