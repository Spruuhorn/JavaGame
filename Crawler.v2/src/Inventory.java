import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Inventory extends JFrame {
	//adaded creation of player and jbutton
	//added action listener to button and function to close menu and send player back into free mode
	//intended to mimic 'close' and 'leave' when closing inventories

	JTextArea list = new JTextArea();

	public Inventory() {
		super("Inventory");
		setSize(300, 500);
		setVisible(true);
		add(new JScrollPane(list));
		list.setEditable(false);
	}

	void showInv(ArrayList<Item> inv) {

		for (int i = 0; i < inv.size(); i++) {
			if (inv.get(i).getIsEquipped() && inv.get(i).getType().equalsIgnoreCase("weapon")) {
				list.append("\nWeapon: " + inv.get(i).getName() + "\n");
			}

			if (inv.get(i).getIsEquipped() && inv.get(i).getType().equalsIgnoreCase("magic")) {
				list.append("\nMagic: " + inv.get(i).getName() + "\n");
			}
		}

		for (int i = 0; i < inv.size(); i++) {
			list.append("\n " + i + ". " + inv.get(i).getName() + " (" + inv.get(i).getValue() + "g)");
		}
	}

	void NPCshowInv(ArrayList<Item> inv) {
		for (int i = 0; i < inv.size(); i++) {
			list.append("\n " + i + ". " + inv.get(i).getName() + " (" + inv.get(i).getValue() + "g)");
		}
	}

}
