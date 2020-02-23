import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.*;

public class Screen extends JPanel implements ActionListener {
	
    private Timer timer;
    
    private static final int k = 1000;
    
    private int WIDTH = 900;
    private int HEIGHT = 900;
    private int DELAY = 15;
    
    private int dr = 6;
    
    MovingCharge mc = new MovingCharge();
    ArrayList<Charge> charges = new ArrayList<Charge>();
    
    private int radiusCorrection = mc.getR()/2;
    
    public Screen() {

        initScene();
        
    }

    private void initScene() {
    	
    	charges.add(new StillCharge(500, 500, 25, 1));

        addKeyListener(new TAdapter());
        addMouseListener(new Mouse());
        setFocusable(true);
        setBackground(Color.WHITE);
        
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);
        drawTestPoints(g);

    }

    private void drawTestPoints(Graphics g) {
    	
    	g.fillOval(mc.getX(), mc.getY(), mc.getR(), mc.getR());
    	
    	for(int i = 0; i < charges.size(); i++) {
    	g.fillOval(charges.get(i).getX() - radiusCorrection, charges.get(i).getY() - radiusCorrection, charges.get(i).getR(), charges.get(i).getR());
    	}
    	
    	for(int row = 0; row < WIDTH / dr ; row++) {
    		for(int col = 0; col < HEIGHT / dr; col++) {
    			g.setColor(Voltage(row * dr, col * dr, mc.getX() - radiusCorrection, mc.getY() - radiusCorrection));
                g.fillOval(row * dr, col * dr, dr, dr);
    		}
    	}
        
    }

    public void actionPerformed(ActionEvent e) {
    	
    	mc.move();
    	repaint();
    	
    }
    
    private Color Voltage(int px, int py, int mx, int my) {
    	
    	double v = 0;
    	
    	double rm = Math.sqrt(Math.pow(mx - px, 2) + Math.pow(my - py, 2));
    	v += k * (mc.getQ() / rm);
    	
    	for(int i = 0; i < charges.size(); i++) {
    		double r = Math.sqrt(Math.pow(charges.get(i).getX() - px, 2) + Math.pow(charges.get(i).getY() - py, 2));
    		v += k * (charges.get(i).getQ() / r);
    	}
    	
    	if(v > 10 || v < -10) {
    		return Color.RED;
    	} else if (v > 8 || v < -8) {
    		return Color.ORANGE;
    	} else if(v > 6 || v < -6) {
    		return Color.YELLOW;
    	} else if(v > 4 || v < -4) {
    		return Color.GREEN;
    	} else if(v > 2 || v < -2) {
    		return Color.BLUE;
    	}
    	
		return Color.BLACK;
    	
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        	mc.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	if(e.getKeyCode() == KeyEvent.VK_R) {
        		for(int i = 1; i < charges.size(); i++) {
        			charges.remove(i);
        		}
        	}
        	mc.keyPressed(e);
		}
    }
    
    private class Mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				charges.add(new StillCharge(e.getX(), e.getY(), 25, 1));
			}
			if(e.getButton() == MouseEvent.BUTTON3) {
				charges.add(new StillCharge(e.getX(), e.getY(), 25, -1));
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

    }
}