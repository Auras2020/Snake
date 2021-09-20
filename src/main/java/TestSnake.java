import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TestSnake extends JApplet{
	public static void main(String[] args){
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Snake");
        window.setContentPane(new MoveSnake(window));
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);     
        
    }
	public TestSnake() {
       this.setContentPane(new MoveSnake(null));
    }
}
