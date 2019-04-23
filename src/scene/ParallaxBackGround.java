/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import game.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kai
 */
public class ParallaxBackGround {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private int width;
	private int height;
	
	private double moveScale;
	
	
	
	public ParallaxBackGround(BufferedImage s, double moveScale) {
			image = s;
			width = 1280;
			height = 720;
			this.moveScale = moveScale;
	}
	
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % width;
		this.y = (y * moveScale) % height;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
 	
	public void setScale(double moveScale) {
		this.moveScale = moveScale;
	}
	
	public double getx() { 
            return x; 
        }
	public double gety() {
            return y; }
	
	public void tick() {
		x += dx;
		while(x <= -width) x += width;
		while(x >= width) x -= width;
		y += dy;
		while(y <= -height) y += height;
		while(y >= height) y -= height;
	}
	
	public void render(Graphics g) {
                
            
		g.drawImage(image, (int)x, (int)y,Game.WIDTH,Game.HEIGHT, null);
                
		if(x < 0) {
			g.drawImage(image, (int)x + Game.WIDTH, (int)y,Game.WIDTH,Game.HEIGHT, null);
		}
		if(x > 0) {
			g.drawImage(image, (int)x - Game.WIDTH, (int)y,Game.WIDTH,Game.HEIGHT, null);
		}
		
	}
	
}

