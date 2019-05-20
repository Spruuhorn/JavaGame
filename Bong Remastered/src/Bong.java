import java.awt.EventQueue;
import javax.swing.JFrame;

public class Bong extends JFrame {

    public Bong() {
        
    	add(new Scene());
        
        setSize(1000, 500);
        setResizable(false);
        
        setTitle("Bong Remastered");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                Bong bong = new Bong();
                bong.setVisible(true);
            }
        });
    }
}