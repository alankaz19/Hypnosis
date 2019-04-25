/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;

/**
 *
 * @author Kai
 */
public interface Updater {
    void tick();
    void render(Graphics g);
}
