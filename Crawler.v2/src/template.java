import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class template extends JFrame {
	
    private JTextArea log = new JTextArea();
    private JTextField action = new JTextField();
	
	public static void main(String[] args) {
		template screen = new template();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
    public template() {
        super("Tales of Evanus");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        // setIconImage(img.getImage());
        setVisible(true);
        add(action, BorderLayout.SOUTH);
        action.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Actions(event.getActionCommand());
                action.setText("");
                /*
                // Uses Players current 'state' to determine available actions
                switch (hero.state) {
                // Ground State
                case "free":
                    Actions(event.getActionCommand());
                    action.setText("");
                    break;
                // In combat
                case "combat":
                    Combat(event.getActionCommand());
                    action.setText("");
                    break;
                // Shopping at blacksmith
                case "shoppingBLA":
                    ShopBLA(event.getActionCommand());
                    action.setText("");
                    break;
                // Shopping at alchemist
                case "shoppingALC":
                    ShopALC(event.getActionCommand());
                    action.setText("");
                    break;
                // Shopping at library
                case "shoppingLIB":
                    ShopLIB(event.getActionCommand());
                    action.setText("");
                    break;
                case "selling":
                    Sell(event.getActionCommand());
                    action.setText("");
                    break;
                // Going through inventory
                case "rummaging":
                    Rummage(event.getActionCommand());
                    action.setText("");
                    break;
                // Adding point to ability after level up
                case "adding":
                    Adding(event.getActionCommand());
                    action.setText("");
                    break;
                    
                case "gambling":
                    Gamble(event.getActionCommand());
                    action.setText("");
                    break;
                    
                case "donating":
                    Donate(event.getActionCommand());
                    action.setText("");
                    break;
                case "end":
                    End(event.getActionCommand());
                    action.setText("");
                    break;
                } 
                
                ...
                
                ETC.
                
                ...
                */
            }
        });
        add(new JScrollPane(log));
        log.setEditable(false);
        log.setBackground(Color.BLACK);
        log.setForeground(Color.WHITE);
    }
    
    private void Actions(String act) {
        log.append(act.toUpperCase() + "\n");
    	// DO SHIT HERE
    }
    
    // I made a method to handle different actions for each state (like combat, shopping, talking, etc.)
}
