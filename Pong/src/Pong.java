import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Pong {
    
    Ball ball = new Ball();
    Menu menu = new Menu();
    
    JLabel desc;
    JLabel score;
    
    JTextField P1Speed = new JTextField();
	JTextField P2Speed = new JTextField();
	JTextField HHitMultiplier = new JTextField();
	JTextField VHitMultiplier = new JTextField();
	JTextField Height = new JTextField();
	JTextField Width = new JTextField();
	
	JLabel p1s = new JLabel();
	JLabel p2s = new JLabel();
	JLabel hhm = new JLabel();
	JLabel vhm = new JLabel();
	JLabel h = new JLabel();
	JLabel w = new JLabel();
    
    ArrayList<JComponent> jcomps = new ArrayList<JComponent>();
    ArrayList<JComponent> jlabels = new ArrayList<JComponent>();

    public static void main(String args[]) {
        Pong pong = new Pong();
    }

    public Pong() {
        EventQueue.invokeLater(new Runnable() {
            
            public void run() {

                JFrame frame = new JFrame("Bong");
                ball.setBackground(Color.BLACK);
                
                desc = new JLabel("Use W and S, or UP and DOWN, to move the paddles. Press the spacebar to play.");
                desc.setForeground(Color.WHITE);
                
                score = new JLabel("");
                score.setForeground(Color.WHITE);
                
                ball.add(score);
                ball.add(desc);
                
                frame.add(ball);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });
        
        initMenuOpt();
        start();
        
    }
    
    public void start() {

        Thread t = new Thread() {
            public void run() {

                while (true) {

                    update();
                    
                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        t.start();
    }
    
    void initMenuOpt() {
    	jcomps.add(P1Speed);
    	jcomps.add(P2Speed);
    	jcomps.add(HHitMultiplier);
    	jcomps.add(VHitMultiplier);
    	jcomps.add(Height);
    	jcomps.add(Width);
    	
    	jlabels.add(p1s);
    	jlabels.add(p2s);
    	jlabels.add(hhm);
    	jlabels.add(vhm);
    	jlabels.add(h);
    	jlabels.add(w);
    }
    
    public void update() {
        ball.move();
    }

    public class Ball extends JPanel {
        
        int WIDTH = 750;
        int HEIGHT = 400;
        
        int x = WIDTH/2;
        int y = HEIGHT/2;
        double vx = 0;
        double vy = 0;
        int radius = 15;
        
        int ORIGINx = x + radius/2;
        int ORIGINy = y + radius/2;
        
        int pHEIGHT = 100;
        int pWIDTH = 25;
        
        int py1 = (HEIGHT/2) - pHEIGHT/2;
        int px1 = 0;
        double pvy1 = 0;
        
        int py2 = (HEIGHT/2) - pHEIGHT/2;
        int px2 = WIDTH - pWIDTH/2;
        double pvy2 = 0;
        
        double HHitM = 1.15;
        double VHitM = 0.4;
        
        int score1 = 0;
        int score2 = 0;

        Ball() {
            InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = getActionMap();
            
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "start");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0), "options");

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up1");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down1");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up2");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down2");
            
            am.put("options", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                	JFrame m = new JFrame("Bong | Options");
                	
                	JTextField P1Speed = new JTextField();
                	JTextField P2Speed = new JTextField();
                	JTextField HHitMultiplier = new JTextField();
                	JTextField VHitMultiplier = new JTextField();
                	JTextField Height = new JTextField();
                	JTextField Width = new JTextField();
                	
                	m.setLayout(null);
                	m.setSize(500, 500);
                	
                	for(int i = 0; i < jcomps.size(); i++) {
                		m.add(jcomps.get(i));
                		m.add(jlabels.get(i));
                		jlabels.get(i).setBounds(10, i * 25, 200, 25);
                		jcomps.get(i).setBounds(100, i * 25, 200, 25);
                	}
                	
                	P1Speed.addActionListener(
                            new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                        pvy1 = Double.parseDouble(e.getActionCommand());
                            }
                        }
                    );
                	
                	P2Speed.addActionListener(
                            new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                        pvy2 = Double.parseDouble(e.getActionCommand());
                            }
                        }
                    );
                	
                	HHitMultiplier.addActionListener(
                            new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                        HHitM = Double.parseDouble(e.getActionCommand());
                            }
                        }
                    );
                	
                	VHitMultiplier.addActionListener(
                            new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                        VHitM = Double.parseDouble(e.getActionCommand());
                            }
                        }
                    );
                	
                	Height.addActionListener(
                            new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                        HEIGHT = Integer.parseInt(e.getActionCommand());
                            }
                        }
                    );
                	
                	Width.addActionListener(
                            new ActionListener() {

                                public void actionPerformed(ActionEvent e) {
                                        WIDTH = Integer.parseInt(e.getActionCommand());
                            }
                        }
                    );
                	
                	m.add(menu);
                	m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                	m.pack();
                	m.setLocationRelativeTo(null);
                	m.setVisible(true);
                }
            });
            
            am.put("start", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                	remove(desc);
                    setSpeed();
                }
            });
            
            am.put("up1", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    pvy1 = -5;
                    repaint();
                }
            });
            
            am.put("down1", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    pvy1 = 5;
                    repaint();
                }
            });
            
            am.put("up2", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    pvy2 = -5;
                    repaint();
                }
            });
            
            am.put("down2", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    pvy2 = 5;
                    repaint();
                }
            });
        }
        
        public void setSpeed() {
        	Random r = new Random();
        	Random dir = new Random();
        	
        	vx = 7;
        	vy = r.nextInt(5) + 1;
        	
        	if(dir.nextInt(9) < 5) {
        		vx *= -1;
        	}
        }
        
        public void move() {
            x += vx;
            y += vy;
            
            py1 += pvy1;
            py2 += pvy2;
            
            
            if(x < 0) {
            	
            	score2++;
                x = WIDTH/2;
                y = HEIGHT/2;
                score.setText(score1 + "     " + score2);
                setSpeed();
                
            } else if(x > WIDTH) {
            	
            	score1++;
                x = WIDTH/2;
                y = HEIGHT/2;
                score.setText(score1 + "     " + score2);
                setSpeed();
                
            }
            
            //The value '10' in the collision detection is a correction factor.
            
            if((y) < 0) {
                vy *= -1;
            } else if ((y > HEIGHT)) {
                vy *= -1;
            }
            
            if(py1 < 0) {
            	
            	py1 = 5;
                pvy1 *= -1;
                
            } else if(py1 + pHEIGHT > HEIGHT + 10) {
            	
            	py1 = HEIGHT - (pHEIGHT + 5);
                pvy1 *= -1;
                
            }
            
            if(py2 < 0) {
            	
            	py2 = 5;
                pvy2 *= -1;
                
            } else if(py2 + pHEIGHT> HEIGHT + 10) {
            	
            	py2 = HEIGHT - (pHEIGHT + 5);
                pvy2 *= -1;
                
            }
            
            if(y > py1 && y + radius < py1 + pHEIGHT && x < px1 + pWIDTH) {
                
                vx *= -1.1;
                vy += 0.4 * pvy1;
                x = px1 + pWIDTH;
                
            } else if (y > py2 && y + radius < py2 + pHEIGHT && x + radius/2 > px2) {
                
                vx *= -1.1;
                vy += 0.4 * pvy2;
                x = WIDTH - (radius + pWIDTH/2);
                
            }
            
            repaint();
        }

        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.WHITE);
            g2.fillOval(x, y, radius, radius);
            
            g2.setColor(Color.WHITE);
            g2.fillRect(px1, py1, 25, 100);
            
            /*
            g2.setColor(Color.RED);
            g2.fillRect(x + radius/2, y + radius/2, 2, 2);
            */
            
            g2.setColor(Color.WHITE);
            g2.fillRect(px2, py2, 25, 100);
        }
    }
    
    public class Menu extends JPanel {
    	
    	public Dimension getPreferredSize() {
            return new Dimension(500,500);
        }
    }

}
