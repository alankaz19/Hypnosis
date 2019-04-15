/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Kai
 */
public class Main extends javax.swing.JFrame{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Main jFrame = new Main();
    jFrame.setSize(1024, 768);
    jFrame.setTitle("Hypnosis");
    
    GamePanel jp = new GamePanel();
    jFrame.add(jp);
    jFrame.setVisible(true);
    jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    jFrame.setResizable(true);
    Timer timer = new Timer(25, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            jFrame.repaint();
        }
    });
    timer.start();
    }
    
    
}
