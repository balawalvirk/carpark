import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI extends JPanel implements KeyListener {

    private double noOfSpacesAvailable_double = 40;
	private static String spacesAvailable = UIClientBridge.noOfCarSpacesAvailable;
	private boolean isParkingSpacesVisible = false;
    
	public static void main(String[] s) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new UI());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
	
    public UI() {
        this.setPreferredSize(new Dimension(500, 500));
        addKeyListener((KeyListener) this);
        repaint();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    public void paintComponent(Graphics g) {
    		if(!isParkingSpacesVisible)
    			g.drawString("Available parking spaces = " + "Please press \"I\" or \"O\"", 150, 250);
        g.clearRect(0, 0, getWidth(), getHeight());
        
       // updateVariables();
        if(isParkingSpacesVisible)
        		g.drawString("Available parking spaces = " + spacesAvailable, 150, 250);
    }

    public void keyTyped(KeyEvent e) {
    		if(e.getKeyChar() == 'i' && noOfSpacesAvailable_double > 0)
    		{
    			isParkingSpacesVisible = true;
    			UIClientBridge uiClientBridge = new UIClientBridge("In");
    		}
    		if(e.getKeyChar() == 'o' && noOfSpacesAvailable_double < 40)
    		{
    			isParkingSpacesVisible = true;
    			UIClientBridge uiClientBridge = new UIClientBridge("Out");
    		}
        repaint();
    }
    
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) { }
    
    private void updateVariables() {
    		spacesAvailable = UIClientBridge.noOfCarSpacesAvailable;
		noOfSpacesAvailable_double = Double.parseDouble(spacesAvailable);
    }
}