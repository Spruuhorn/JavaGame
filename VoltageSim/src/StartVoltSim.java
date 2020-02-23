import java.awt.EventQueue;
import javax.swing.JFrame;

public class StartVoltSim extends JFrame {

    public StartVoltSim() {
        
    	add(new Screen());
        
        setSize(900, 900);
        setResizable(false);
        
        setTitle("Voltage Simulator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                StartVoltSim vs = new StartVoltSim();
                vs.setVisible(true);
            }
        });
    }
}