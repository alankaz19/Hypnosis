/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Kai
 */
public class GamePanel extends javax.swing.JPanel{
    
    public static int WIDTH;
    public static int HIGHT;
    
    
    class KeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_D:                 
                    break;
                case KeyEvent.VK_A:                  
                    break;
                case KeyEvent.VK_S:
                    break;
                case KeyEvent.VK_W:
                    break;
                case KeyEvent.VK_Z:
                    break;
                case KeyEvent.VK_X:
                    break;
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_D:
                    break;
                case KeyEvent.VK_A:
                    break;
                case KeyEvent.VK_S:
                    break;
                case KeyEvent.VK_W:
                    break;
               
            }
        }
        
        
        
    }
    
    public GamePanel() {
        this.addKeyListener(new KeyListener());
        this.setFocusable(true);

        Timer t1 = new Timer(25, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
        });
        t1.start();//開始計時
    }
    
    @Override
    public void paintComponent(Graphics g){

    }
}
