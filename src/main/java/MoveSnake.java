import java.awt.Color;


//import java.awt.Dimension;
//import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MoveSnake extends JPanel implements Runnable, KeyListener{
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;
    
    private Thread thread;
    private boolean running;
 
    private BodyPart b;
    private ArrayList<BodyPart> snake;
 
    private Apple apple;
    private ArrayList<Apple> apples;
    
    private Random r;
    
    private int xCoor = 10;
    private int yCoor = 10;
    private int size = 5;
 
    private boolean right = true, left = false, up = false, down =false;
    
    private int ticks = 0;
    
    public MoveSnake(JFrame window) {
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
 
        r = new Random();
        
        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();
        start();
    }
    
    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
        }
        if(apples.size() == 0) {
            int xCoor = r.nextInt(49);
            int yCoor = r.nextInt(49);
            
            apple = new Apple(xCoor, yCoor, 10);
            apples.add(apple);
        }
        
        for(int i = 0; i < apples.size(); i++) {
            if(xCoor == apples.get(i).getxCoor() && 
                    yCoor == apples.get(i).getyCoor()) {
                size++;
                apples.remove(i);
                i++;
            }
        }
        
        for(int i =0; i < snake.size(); i++) {
            if(xCoor == snake.get(i).getxCoor() && 
                    yCoor == snake.get(i).getyCoor()) {
                if(i!= snake.size() - 1 ){
                	stop();
                }
            }
        }
        if(xCoor < 0) {
        	xCoor=50;
        }
        if(xCoor>50) {
        	xCoor=0;
        }
        if(yCoor<0) {
        	yCoor=50;
        }
        if(yCoor>50) {
        	yCoor=0;
        }
        
        ticks++;
        
        if(ticks > apple.getNrTicks()) {//speed of the snake
            if(right) xCoor++;
            if(left) xCoor--;
            if(up) yCoor--;
            if(down) yCoor++;
            
            ticks = 0;
            
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
            
            if(snake.size() > size){
                snake.remove(0);
            }
        }
    }
 
    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
 
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }
        for(int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(g, snake.size());
        }
        
        for(int i =0; i < snake.size(); i++) {
            if(xCoor == snake.get(i).getxCoor() && 
                    yCoor == snake.get(i).getyCoor()) {
                if(i != snake.size() - 1) {
                	g.drawString("GAME OVER", 200, 250);
                }
            }
        }
 
    }
 
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
 
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT ) {
        	if(left) {
        		left=true;
                up = false;
                down = false;
                right = false;
                
        	}
        	else {
        		left=false;
                up = false;
                down = false;
                right = true;
        	}
        }
        if(key == KeyEvent.VK_LEFT) {
        	if(right) {
        		left=false;
                up = false;
                down = false;
                right = true;
                
        	}
        	else {
        		right=false;
                up = false;
                down = false;
                left = true;
        	}
        }
        if(key == KeyEvent.VK_UP) {
        	if(down) {
        		left=false;
                up = false;
                down = true;
                right = false;
        	}
        	else {
        		down=false;
                left = false;
                right = false;
                up = true;
        	}
        }
        if(key == KeyEvent.VK_DOWN ) {
        	if(up) {
        		left=false;
                up = true;
                down = false;
                right = false;
        	}
        	else {
        		up=false;
                left = false;
                right = false;
                down = true;
        	}	
        }
        if(key==KeyEvent.VK_ENTER) {
        	if(running==false) {
        	    xCoor = 10;
        	    yCoor = 10;
        	    size = 5;
        	    right = true; left = false; up = false; down =false;
        	    snake = new ArrayList<BodyPart>();
                apples = new ArrayList<Apple>();
                repaint();
        	    start();
        	}
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
