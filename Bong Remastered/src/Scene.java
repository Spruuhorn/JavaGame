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

import javax.swing.JPanel;
import javax.swing.Timer;

public class Scene extends JPanel implements ActionListener {

    private Timer timer;
    private Timer spawn;
    
    private MenuControl mc;
    
    private Paddle paddle1;
    private Rectangle HBp1;
    
    private Paddle2 paddle2;
    private Rectangle HBp2;
    
    private Ball ball;
    Rectangle HBball;
    
    private int WIDTH = 1000;
    private int HEIGHT = 500;
    private int DELAY = 15;
    
    public Scene() {

        initScene();
        
    }

    private void initScene() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        paddle1 = new Paddle(0, (HEIGHT / 2) - 50, 25, 100);
        
        paddle2 = new Paddle2(WIDTH - 25, (HEIGHT / 2) - 50, 25, 100);
        
        ball = new Ball(WIDTH / 2, HEIGHT / 2, 15);
        
        mc = new MenuControl();

        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(mc.getIngame()) {
        	drawObjects(g);
        } else {
        	drawMain(g);
        }

    }
    
    private void drawMain(Graphics g) {
    	Font font = new Font("Helvetica", Font.BOLD, 64);
		FontMetrics fm = getFontMetrics(font);
		g.setColor(Color.WHITE);
		g.setFont(font);
		
		String Title = "Bong Remastered";
		String opt1 = "Press ENTER";
		
		g.drawString(Title, (WIDTH - fm.stringWidth(Title)) / 2, 100);
		
		Font font2 = new Font("Helvetica", Font.PLAIN, 32);
		FontMetrics fm2 = getFontMetrics(font2);
		g.setFont(font2);
		
		g.drawString(opt1, (WIDTH - fm2.stringWidth(opt1)) / 2, 200);
    }

    private void drawObjects(Graphics g) {
    	
    		Font font = new Font("Helvetica", Font.BOLD, 32);
    		FontMetrics fm = getFontMetrics(font);
			g.setFont(font);

        	g.setColor(Color.WHITE);
            g.fillRect(0, paddle1.getY(), 25, 100);
            
            g.setColor(Color.WHITE);
            g.fillRect(paddle2.getX(), paddle2.getY(), 25, 100);
            
            g.setColor(Color.WHITE);
            g.fillOval(ball.getX(), ball.getY(), 15, 15);
            
            g.drawString(ball.score(), (WIDTH - fm.stringWidth(ball.score())) / 2, 32);
            
            //Hitbox for ball
            /*
            g.setColor(Color.RED);
            g.fillRect(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight());
            */
    }
    
    public void drawAbout(Graphics g) {
    	
    }

    public void actionPerformed(ActionEvent e) {
    	
    	if(mc.getIngame()) {
    		updatePaddle();
            checkCollisions();
            repaint();
    	}
    }

    private void updatePaddle() {
    	if(mc.getIngame()) {
            paddle1.move();
            paddle2.move();
            ball.move();
    	}
    }

    public void checkCollisions() {
    	
    	HBp1 = paddle1.getBounds();
    	HBp2 = paddle2.getBounds();
        HBball = ball.getBounds();
    	
    	if(HBball.intersects(HBp1)) {
    		ball.hit();
    	} else if (HBball.intersects(HBp2)) {
    		ball.hit();
    	}
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
            mc.keyPressed(e);
        
		}
    }
}